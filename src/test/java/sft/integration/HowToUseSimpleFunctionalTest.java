package sft.integration;

import org.junit.runner.RunWith;
import sft.SimpleFunctionalTest;
import sft.integration.extend.Extend;
import sft.integration.hack.Hack;
import sft.integration.set.Settings;
import sft.integration.use.Usages;


/*
<div>
    SimpleFunctionalTest is a java testing framework plugin for jUnit.<br/><br/>

    It is designed to be:
    <ul>
        <li>easy to adopt</li>
        <li>easy to use</li>
        <li>easy to extend</li>
    </ul>

    This framework aimed any developer that want to introduce functional testing in java project.
</div>
<h2>Features</h2>
<div>
    <h3>Easy to adopt</h3>
    <div>
        SFT is full java.<br/>
        SFT is a jUnit plugin.<br/>
        You just have to import SFT dependency to your project and mark class hosting functional test with&nbsp;@RunWith(SimpleFunctionalTest.class)
    </div>
    <h3>Easy to use</h3>
    <div>
        Implicit conversion :
        <ul>
            <li>classes implements UseCases</li>
            <li>public test methods implements Scenarios</li>
            <li>non-public methods implements Fixtures (code that glue the system under test)</li>
            <li>public fields implements SubUseCases</li>
            <li>camelCase and underscore java name are humanized</li>
            <li>beforeClass and afterClass JUnit annotation rules use case context</li>
            <li>before and after JUnit annotation rules scenario context</li>
        </ul>
        Functional results are published in HTML.
    </div>
    <h3>Easy to extend</h3>
    <div>
        SFT try to follows this library development guidance:
        <ul>
            <li>Use: 80% of features can be directly used with less code as possible</li>
            <li>Set:&nbsp;other features can be used by settings</li>
            <li>Hack:&nbsp;additional features can be developed using interfaces and injection points</li>
            <li>Extend:&nbsp;additional features can be shared under plugin</li>
            <li>Enhance:&nbsp;for all other needs, welcome aboard: propose feature request to SFT</li>
    </div>

</div>
<h2> IN PROGRESS </h2>
<ul>
    <li><a href="https://maven.apache.org/guides/mini/guide-central-repository-upload.html">mvnrepo upload</a></li>
</ul>
<h2> TODO </h2>
<ul>
    <li> Display context data </li>
    <li>enhance parametrization (configurator - default css / file inclusion): zimit + google font</li>
    <li>enhance parametrization (configurator)</li>
    <li>enhance hacking (extract interface by responsibility)</li>
    <li>enhance extension (allow injection)</li>
    <li>write test result in LaTeX</li>
    <li>adding decorator to SubUseCase</li>
</ul>

*/
@RunWith(SimpleFunctionalTest.class)
public class HowToUseSimpleFunctionalTest {
    public Usages usages= new Usages();
    public Settings settings = new Settings();
    public Hack hack = new Hack();
    public Extend extend = new Extend();
}