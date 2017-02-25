package com.bdd.step;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ArrayList;




import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.ConsoleOutput;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.reporters.StoryReporterBuilder.Format;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.jbehave.core.steps.ParameterConverters.ExamplesTableConverter;
import org.jbehave.core.io.CodeLocations;


public class TraderStories extends JUnitStories {
 
    private static final Format TXT = null;
	private final CrossReference xref = new CrossReference();
 
    public TraderStories() {
        configuredEmbedder().embedderControls().doGenerateViewAfterStories(true).doIgnoreFailureInStories(false)
                .doIgnoreFailureInView(true).doVerboseFailures(true).useThreads(2).useStoryTimeoutInSecs(60);
        //configuredEmbedder().useEmbedderControls(new PropertyBasedEmbedderControls());
    }
 
    @Override
    public Configuration configuration() {
    	
        //Keywords keywords = new LocalizedKeywords(Locale.SIMPLIFIED_CHINESE, "i18n/keywords", TraderStories.class.getClassLoader());
        //useConfiguration(new MostUsefulConfiguration()
        // 执行过程输出到控制台
        //.useDefaultStoryReporter(new ConsoleOutput(keywords)));
        
        Class<? extends Embeddable> embeddableClass = this.getClass();
        Properties viewResources = new Properties();
        viewResources.put("decorateNonHtml", "true");
        viewResources.put("reports", "ftl/jbehave-reports-with-totals.ftl");
        ParameterConverters parameterConverters = new ParameterConverters();
        ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(new LocalizedKeywords(), new LoadFromClasspath(embeddableClass), parameterConverters);
        parameterConverters.addConverters(new DateConverter(new SimpleDateFormat("yyyy-MM-dd")),
                new ExamplesTableConverter(examplesTableFactory));
        return new MostUsefulConfiguration()
            .useStoryLoader(new LoadFromClasspath(embeddableClass))
            .useStoryParser(new RegexStoryParser(examplesTableFactory)) 
            .useStoryReporterBuilder(new StoryReporterBuilder()
                .withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
                .withDefaultFormats()
                .withViewResources(viewResources)
               // .withFormats(CONSOLE, TXT, HTML_TEMPLATE, XML_TEMPLATE)
                .withFailureTrace(true)
                .withFailureTraceCompression(true)                
                .withCrossReference(xref)) 
            .useParameterConverters(parameterConverters)                     
            // use '%' instead of '$' to identify parameters
            .useStepPatternParser(new RegexPrefixCapturingPatternParser(
                            "%")) 
            .useStepMonitor(xref.getStepMonitor());                               
    }
 
 
     
@Override
    protected List<String> storyPaths() {
    	StoryFinder finder = new StoryFinder();
    	return new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()),"**/*.story","");    
    
    }




         
}