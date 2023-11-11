package cz.czechitas.automation;

import com.fasterxml.jackson.databind.deser.BasicDeserializerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LowCodeAutoTest2 extends TestRunner {

    private String user1 = "automat@test.cz";
    private String user1Pass = "Atest1";
    private String user2 = "tester@druhy.cz";
    private String user2Pass = "TesterD1";
    private String newpassword = "ZmenaHesla1"
    private String name = browser.generateRandomName(7);

    private void LoginUser1(String user, String password) {
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail(user);
        browser.loginSection.insertPassword(password);
        browser.loginSection.clickLoginButton();
    }
    private void LoginUser2(String user, String password) {
        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail(user);
        browser.loginSection.insertPassword(password);
        browser.loginSection.clickLoginButton();
    }

    /*Zkouska parametrizovaneho testu, nen9 pot5eba to takto psat*/
    @ParameterizedTest
    @ValueSource(strings = {"22834958"})
    @DisplayName("Ucitele-ICO-Skola v prirode")
    void shouldPutICOAndChooseSkolavPrirode(String ICO){
        browser.headerMenu.goToKindergartenAndSchoolSection();
        browser.orderSection.insertICO(ICO);
        browser.orderSection.selectSchoolInNatureOption();
    }

    @Test
    @DisplayName("Ucitele-ICO-Skola v prirode")
    void shouldPutICOAndChooseSkolavPrirode(){
        browser.headerMenu.goToKindergartenAndSchoolSection();
        browser.orderSection.insertICO("22834958");
        browser.orderSection.selectSchoolInNatureOption();
        browser.orderSection.insertChildrenCount(15);
    }

    /*Napište test, který se přihlásí do aplikace, přejde pomocí horního menu do sekce Přihlášky
     a ověří, že v tabulce přihlášek jsou sloupce pojmenované Jméno a Kategorie*/
    @Test
    @DisplayName("Prihlaseni-prihlasky-kontrola jmeno a kategrie")
    void shouldLogInGoToApplicationsAndControlTheSections(){
        LoginUser1(user1, user1Pass);
        browser.headerMenu.goToApplicationsSection();
        asserter.applicationSection.checkColumnExists("Jméno");
        asserter.applicationSection.checkColumnExists("Kategorie");
        browser.loginSection.logout();
    }

    @Test
    @DisplayName("Komplexni test podle zadani")
    void KomplexniTest(){
        LoginUser1(user1,user1Pass);
        browser.headerMenu.goToApplicationsSection();
        browser.applicationSection.clickCreateNewApplicationButton();
        browser.applicationSection.selectProgrammingSection();
        browser.applicationSection.clickCreateApplicationButton();
        browser.applicationDetailsSection.selectTerm("05.02. - 09.02.2024");
        browser.applicationDetailsSection.insertStudentFirstName("Josefina");
        browser.applicationDetailsSection.insertStudentLastName("Testovaci");
        browser.applicationDetailsSection.insertBirthdate("17.12.2007");
        browser.applicationDetailsSection.insertNote("Test note");
        browser.applicationDetailsSection.selectCashPaymentMethod();
        browser.applicationDetailsSection.clickAcceptTermsCheckbox();
        browser.applicationDetailsSection.clickCreateApplicationButton();
        asserter.applicationDetailAction.checkFirstName("Josefina");
        asserter.applicationDetailAction.checkLastName("Testovaci");
        asserter.applicationDetailAction.checkDateOfBirth("17.12.2007");
        asserter.applicationDetailAction.checkNote("Test note");
        asserter.applicationDetailAction.checkTerm("05.02. - 09.02.2024");
        asserter.applicationDetailAction.checkPaymentMethod("Hotově");
        browser.headerMenu.goToHomePage();
        browser.loginSection.logout();
    }
    @Test
    @DisplayName("Komplexni test podle zadani 2")
    void KomplexniTestDruhy(){
        LoginUser1(user1,user1Pass);
        browser.headerMenu.goToApplicationsSection();
        browser.applicationSection.search("Pepinova");
        browser.applicationSection.clickEditFirstApplicationButton();
        browser.applicationDetailsSection.selectBankTransferPaymentMethod();
        browser.applicationDetailsSection.clickEditApplicationButton();
        browser.applicationSection.search("Pepinova");
        browser.applicationSection.openFirstApplicationDetailsPage();
        asserter.applicationDetailAction.checkPaymentMethod("Bankovní převod");
        asserter.applicationDetailAction.checkRemainingAmountToPay("1 800 Kč");
        asserter.applicationDetailAction.checkMessageContainsStudentLastName("Pepinova");
        browser.loginSection.logout();
    }


        @Test
        @DisplayName("Komplexni test podle zadani 6")
        void KomplexniTestSesty(){
            LoginUser2(user2,user2Pass);
            browser.headerMenu.goToApplicationsSection();
            browser.applicationSection.clickCreateNewApplicationButton();
            browser.applicationSection.selectProgrammingSection();
            browser.applicationSection.clickCreateApplicationButton();
            browser.applicationDetailsSection.selectTerm("05.02. - 09.02.2024");
            browser.applicationDetailsSection.insertStudentFirstName("Petra");
            browser.applicationDetailsSection.insertStudentLastName(name);
            browser.applicationDetailsSection.insertBirthdate("17.12.2007");
            browser.applicationDetailsSection.selectCashPaymentMethod();
            browser.applicationDetailsSection.clickAcceptTermsCheckbox();
            browser.applicationDetailsSection.clickCreateApplicationButton();
            browser.headerMenu.goToApplicationsSection();
            browser.applicationSection.search(name);
            asserter.applicationSection.checkNumberOfApplications(1);
            browser.loginSection.logout();
            LoginUser1(user1,user1Pass);
            browser.headerMenu.goToApplicationsSection();
            browser.applicationSection.search(name);
            asserter.applicationSection.checkApplicationsTableIsEmpty();
            browser.loginSection.logout();
    }
    @Test
    @DisplayName("Komplexni test podle zadani 7")
    void KomplexniTestSedmy() {
        LoginUser2(user2,user2Pass);

        browser.profileSection.goToProfilePage();
        browser.profileSection.insertPassword(newpassword);
        browser.profileSection.insertPasswordVerification(newpassword);
        browser.profileSection.clickChangeButton();
        browser.headerMenu.goToHomePage();
        browser.loginSection.logout();

        browser.loginSection.clickLoginMenuLink();
        browser.loginSection.insertEmail(user2);
        browser.loginSection.insertPassword(newpassword);
        browser.loginSection.clickLoginButton();

        browser.profileSection.goToProfilePage();
        browser.profileSection.insertPassword(user2Pass);
        browser.profileSection.insertPasswordVerification(user2Pass);
        browser.profileSection.clickChangeButton();
        browser.headerMenu.goToHomePage();
        browser.loginSection.logout();

        LoginUser2(user2,user2Pass);
        asserter.checkIsLoggedIn();
        browser.loginSection.logout();

    }

}
