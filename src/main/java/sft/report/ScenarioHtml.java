/*******************************************************************************
 * Copyright (c) 2013 Sylvain Lézier.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sylvain Lézier - initial implementation
 *******************************************************************************/
package sft.report;


import sft.Fixture;
import sft.UseCase;
import sft.javalang.parser.FixtureCall;
import sft.javalang.parser.TestContext;
import sft.javalang.parser.TestMethod;
import sft.result.ScenarioResult;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class ScenarioHtml {

    private final Css css;
    private final UseCase useCase;
    private final Writer htmlWriter;
    private final TestMethod testMethod;
    private final ScenarioResult scenarioResult;
    private final TestContext after;
    private final TestContext before;

    public ScenarioHtml(Css css, UseCase useCase, Writer htmlWriter, TestMethod testMethod, ScenarioResult scenarioResult, TestContext before, TestContext after) {
        this.useCase = useCase;
        this.htmlWriter = htmlWriter;
        this.testMethod = testMethod;
        this.scenarioResult = scenarioResult;
        this.css = css;
        this.before= before;
        this.after= after;

    }

    public void write() throws IOException {
        htmlWriter.write("<div class=\"scenario " + css.convertIssue(scenarioResult.issue) + " panel panel-default\">");
        htmlWriter.write("<div class=\"panel-heading\"><h3><span class=\"scenarioName\">" + scenarioResult.scenario.getName() + "</span></h3></div>\n");


        if(testMethod.getComment()!=null){
            htmlWriter.write("<div class=\"comment\">"+testMethod.getComment()+"</div>");
        }
        if( useCase.beforeScenario != null){
            htmlWriter.write("<div class=\"beforeScenario panel-body\">");
            for( FixtureCall fixtureCall: before.fixtureCalls){
                Fixture fixture = useCase.getFixtureByMethodName(fixtureCall.name);
                htmlWriter.write("<div><span>" + fixture.getText(fixtureCall.parameters) + "</span></div>\n");
            }
            htmlWriter.write("<hr/></div>");
        }

        htmlWriter.write("<div class=\"panel-body\">\n");
        if(scenarioResult.issue == Issue.FAILED){
            writeTestFailed();
        } else {
            writeTestSucceededOrIgnored();
        }
        htmlWriter.write("</div>\n");

        if( useCase.afterScenario != null){
            htmlWriter.write("<div class=\"afterScenario panel-body\"><hr/>");

            for( FixtureCall fixtureCall: after.fixtureCalls){
                Fixture fixture = useCase.getFixtureByMethodName(fixtureCall.name);
                htmlWriter.write("<div><span>" + fixture.getText(fixtureCall.parameters) + "</span></div>\n");
            }
            htmlWriter.write("</div>");
        }


        htmlWriter.write("</div>\n");
    }

    private void writeTestFailed() throws IOException {
        Fixture failedCall = scenarioResult.getFailedCall();

        boolean failureAppend = false;

        for (FixtureCall testFixture : testMethod.fixtureCalls) {
            Fixture fixture = useCase.getFixtureByMethodName(testFixture.name);

            Issue testIssue ;
            if(fixture == failedCall && scenarioResult.getFailedLine() == testFixture.line){
                failureAppend =true;
                testIssue = Issue.FAILED;
            }else if(! failureAppend){
                testIssue = Issue.SUCCEEDED;
            }else{
                testIssue = Issue.IGNORED;
            }
            htmlWriter.write("<div class=\"instruction "+css.convertIssue(testIssue) +"\"><span>" + fixture.getText(testFixture.parameters) + "</span></div>\n");
        }

        Throwable failure = scenarioResult.getFailure();
        htmlWriter.write("<div class=\"exception\"><a onClick=\"$(this).next().toggle()\" >"+failure.getClass().getSimpleName()+": "+ failure.getMessage()+"</a>" +
                "<pre class=\"stacktrace pre-scrollable\" >");
        PrintWriter printWriter = new PrintWriter(htmlWriter);
        failure.printStackTrace(printWriter);
        htmlWriter.write("</pre></div>");
    }

    private void writeTestSucceededOrIgnored() throws IOException {
        for (FixtureCall testFixture : testMethod.fixtureCalls) {
            Fixture fixture = useCase.getFixtureByMethodName(testFixture.name);
            htmlWriter.write("<div class=\"instruction "+css.convertIssue(scenarioResult.issue) +"\"><span>" + fixture.getText(testFixture.parameters) + "</span></div>\n");
        }
    }


}