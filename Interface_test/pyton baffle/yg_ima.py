# -*- coding: UTF-8-*-  
from SocketServer import ThreadingMixIn
from BaseHTTPServer import HTTPServer,BaseHTTPRequestHandler
import BaseHTTPServer
import posixpath
import urllib2
import time
import httplib
from xml.etree.ElementTree import ElementTree
from xml.etree import ElementTree as ET 
import re
import ftplib 
from ftplib import FTP



postHeaders = {"Content-Type":"text/xml"}



body= '''<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<Root cseq="xxxcseqxxx">
<ADIIngestRes message="xxxassetidxxx:xxxnewassetidxxx" responseCode="200"/>
</Root>
'''
body1='''<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<Root cseq="xxxcseqxxx">
<DelRes token="xxxtokenxxx" stamp="xxxstampxxx">
<Success><Asset assetId="xxxassetidxxx"/></Success>
<Failure/>
</DelRes>
</Root>'''
class myHandler(BaseHTTPRequestHandler):
    
    def do_GET(self):
        self.send_response(200)
        self.send_header('Content-type','text/html')
        self.send_header('Uri',self.path)
        self.end_headers()
        self.wfile.write("hi multi threading test!\n")
        
    def do_POST(self):
        
        if self.headers.has_key('content-length'):
            length= int( self.headers['content-length'] )
            print length
            self.data = self.rfile.read(length)
            self.rfile.close()
        else:
            print 'no content-length'
        
        taskId = ''
        
        print self.data
        xmlRoot=ET.fromstring(self.data)
        Element=xmlRoot.find('.//DelAssets')
        if Element is None:
            ftp=xmlRoot.find('./ADIIngest').attrib.get('ftp')
            print ftp



            p=re.compile('/*')
            xmlname=p.split(ftp)
            filename=xmlname[3]

            ftp = ftplib.FTP()  
            ftp.connect('192.168.88.64', '21')
            ftp.login('icms1','1')
            ftp.cwd("yg_ima")
            time.sleep(2)
            time2=time.localtime(time.time())
            t2=time.strftime("%Y%m%d%H%M%S",time2)
            DownLocalFilename=t2+".xml"
            f=open(DownLocalFilename, 'wb')
            ftp.retrbinary('RETR ' + filename , f.write , 1024)   
            f.close() 
            ftp.close()


            tree=ElementTree(file=DownLocalFilename)
            root=tree.getroot()
            assetid=root.find('./Asset/Metadata/AMS').attrib.get('Asset_ID')
            cseq=xmlRoot.find('.').attrib.get('cseq')

            str_notify = body
            str_notify = str_notify.replace('xxxassetidxxx',assetid)
            str_notify = str_notify.replace('xxxnewassetidxxx', t2)
            str_notify = str_notify.replace('xxxcseqxxx',cseq)

        else:
            token=xmlRoot.find('.//DelAssets').attrib.get('token')
            cseq=xmlRoot.find('.').attrib.get('cseq')
            stamp=xmlRoot.find('.//DelAssets').attrib.get('stamp')
            assetid=xmlRoot.find('.//Asset').attrib.get('assetId')
            str_notify = body1
            str_notify = str_notify.replace('xxxassetidxxx',assetid)
            str_notify = str_notify.replace('xxxcseqxxx',cseq)
            str_notify = str_notify.replace('xxxstampxxx',stamp)
            str_notify = str_notify.replace('xxxtokenxxx',token)
            time.sleep(2)

            
            

        
        res = str_notify
        
        retData = res
        time.sleep(2)
        print '同步响应信息:'.rjust(100, '-')
        print retData

        self.send_response(200)
        self.send_header('Content-Type', 'text/xml')
        self.send_header('Content-Length', len(retData))
        self.end_headers()
        self.request.send(retData)

        

    
   
    
    
    
    
    
class ThreadingHttpServer(ThreadingMixIn, HTTPServer):
    pass       
    
PORT_NUM=5889
serverAddress=("", PORT_NUM)
server=ThreadingHttpServer(serverAddress, myHandler)
print 'Started httpserver on port ' , PORT_NUM
server.serve_forever() 