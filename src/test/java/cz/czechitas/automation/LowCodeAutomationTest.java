package cz.czechitas.automation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Test class for custom student tests
 *
 * @author Jiri Koudelka
 * @since 1.0.0
 */
final class LowCodeAutomationTest extends TestRunner {

    @Test
    void contactsPageUrlTest(){
        browser.headerMenu.goToContactsSection();
        asserter.checkPageUrl("www.czechitas.cz");
    }
    @Test
    @DisplayName("navody pro rodice")
    void manualsForParents(){
        browser.headerMenu.goToInstructionsAndFormsForParentSection();
    }

    @Test
    @DisplayName("Primestsky tabor")
    void shouldChoosePrimestskyTabor(){
        browser.headerMenu.goToKindergartenAndSchoolSection();
        browser.orderSection.selectSuburbanCampOption();
    }

    @Test
    @DisplayName("Test Verejneho Menu")
    void publicMenuTest(){
        browser.headerMenu.goToContactsSection();
        browser.headerMenu.goToHomePage();
        browser.headerMenu.goToKindergartenAndSchoolSection();
        browser.headerMenu.goToInstructionsAndFormsForParentSection();
        browser.headerMenu.goToCreateApplicationSection();
        browser.headerMenu.goToInstructionsAndFormsForTeacherSection();
    }

    @ParameterizedTest()
    @ValueSource(strings = {"22834958"})
    @DisplayName("VyplneniICO")
    void ICO(String ICO){
        browser.headerMenu.goToKindergartenAndSchoolSection();
        browser.orderSection.insertICO(ICO);
    }

    @Test
    @DisplayName("Vytvoreni prihlasky")
    void applicationCreation(){
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail("automat@test.cz");
        browser.loginSection.insertPassword("Atest1");
        browser.loginSection.clickLoginButton();
        browser.headerMenu.goToCreateApplicationSection();
        browser.applicationSection.selectProgrammingSection();
        browser.applicationSection.clickCreateApplicationButton();
        browser.loginSection.logout();

    }

    @Test
    @DisplayName("Pritomnost programovani")
    void thereIsProgramovani(){
        browser.headerMenu.goToHomePage();
        asserter.checkProgrammingSectionPresense();
    }

    @Test
    @DisplayName("Pritomnost Registrace pro noveho uzivatele")
    void thereIsRegistrationButtonForNewUser(){
        browser.headerMenu.goToCreateApplicationSection();
        asserter.checkRegistrationButtonPresense();

    }

    @Test
    @DisplayName("Spravny zpusob uhrady")
    void checkThePaymentMethod(){
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail("automat@test.cz");
        browser.loginSection.insertPassword("Atest1");
        browser.loginSection.clickLoginButton();
        browser.applicationSection.openFirstApplicationDetailsPage();
        asserter.applicationDetailAction.checkPaymentMethod("Bankovní převod");
        browser.loginSection.logout();

    }
}

