package sft.integration.use;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import sft.Decorate;
import sft.Displayable;
import sft.FixturesHelper;
import sft.SimpleFunctionalTest;
import sft.Text;
import sft.decorators.Breadcrumb;
import sft.integration.fixtures.JUnitHelper;
import sft.integration.fixtures.SftResources;

import java.io.IOException;

/*
    Literal specifications can be wrote within java multi-line comments to document use case or scenario.
 */
@RunWith(SimpleFunctionalTest.class)
@Decorate(decorator = Breadcrumb.class)
@Text("Add literal specifications in your use case: comment usage")
public class CommentUsage {


    @FixturesHelper
    private JUnitHelper jUnitHelper = new JUnitHelper();


    @Test
    public void commentUseCase() throws IOException {
        useCaseExplanationCouldBeWriteInAMultilinesCommentBeforeClassDeclaration();
        itIsDisplayedInTheReportAfterTheUseCaseTitleAndBeforeFirstScenario();
    }

    private void useCaseExplanationCouldBeWriteInAMultilinesCommentBeforeClassDeclaration() throws IOException {
        runCommentUsageTest();
    }

    private void runCommentUsageTest() throws IOException {
        jUnitHelper.run(this.getClass(), sft.integration.use.sut.CommentUsage.class);
    }

    private void itIsDisplayedInTheReportAfterTheUseCaseTitleAndBeforeFirstScenario() throws IOException {
        Assert.assertEquals("use case comment", jUnitHelper.html.select("*.comment").get(0).text());
    }

    @Test
    public void commentScenario() throws IOException {
        scenarioExplanationCouldBeWriteInAMultilinesCommentBeforeMethodDeclaration();
        itIsDisplayedInTheReportAfterTheScenarioTitleAndBeforeScenarioSteps();
    }

    private void scenarioExplanationCouldBeWriteInAMultilinesCommentBeforeMethodDeclaration() throws IOException {
        runCommentUsageTest();
    }

    private void itIsDisplayedInTheReportAfterTheScenarioTitleAndBeforeScenarioSteps() throws IOException {
        Assert.assertEquals("scenario comment", jUnitHelper.html.select("*.scenario *.comment").text());
    }

}
