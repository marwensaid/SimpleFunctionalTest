SFT-tutoriel
============


I. Pr�sentation de SimpleFunctionnalTest

 Les tests deviennent vites indispensables lors de l'�laboration d'un logiciel de qualit�. 
 Ceux-ci sont de plusieurs types en fonction du p�rim�tre test� et sont souvent automatisabes: tests unitaires, tests de charges, tests d'int�garations...
 Dans cette palette d'outil, deux typologie de test permettent de centrer le d�veloppement sur l'expression des besoins:
 les tests fonctionnels (permet de d�crire d'un point de vue client ou utilisateur le logiciel) et les tests de validations (d�termine les objectifs d'un d�veloppement et les moyens pour les valider).

 SimpleFunctionnalTest propose un moyen simple pour un d�veloppeur de r�diger rapidement des tests de validation ou des tests fonctionnels.


II. M�thodes 
 Afin de facilter le d�roullement de ce tutoriels les sources utilis�es sont disponibles sur github https://github.com/slezier/SFT-tutoriel et t�l�chargeable https://github.com/slezier/SFT-tutoriel/archive/master.zip .
 Ce projet utilisera maven 3.1, une JDK 1.6+ et l'IDE de votre choix.
 Un minimum de connaissance sur JUnit 1.4 et les tests utnitaires sont n�cessaires.
 Le projet � couvrir est un distributeur automatique de billets (ATM en anglais) 

III. Premier Test
III.1  R�daction d'un test humainement 'lisible'

 Premier fonctionnalit� � tester: un retrait bancaire classique.
 Ce cas d'utilisation peut faire l'objet de diff�rents 'Sc�narios' de tests: Cas passsant (retrait autoris�), cas alternatifs (retrait refus�) cas en echec (�chec de connexion � la banque).

 Une classe Java sera cr��e par cas d'utilisation. 
 Chaque tests unitaires de cette classe �criront un sc�nario.
 
 Le test doit �tre �crit de la fa�on la plus 'humaine' et la moins 'informatique' possible.

 Dans notre cas la classe de test s'appelera : RetraitBanquaire
 Le premier sc�nario ( retraitAutoris� ) n'est compos� que d'appels de m�thodes (fixtures) d�crivant le sc�nario.
 Ces fixtures permettent d'interagir avec le logiciel; ce sont des m�thodes non publiques.
 Des champs non publiques seront utilis�s pourenrgistrer le contexte associ� au teste.

package bancomat;


public class RetraitBancaire{

  @Test
  public void retraitAutoris�(){
    soitUnUtilisateurAyantUnCompteCredit�De1000Euros();
    quandIlDemandeUnRetraitDe200Euros();
    alorsLeGuichetDistribue200Euros();
    leCompteEstAlorsCr�dit�De800Euros();
  }

  private int compte ;
  private void soitUnUtilisateurAyantUnCompteCredit�De1000Euros(){
    compte = 1000;
  }
  private void quandIlDemandeUnRetraitDe200Euros(){
	guichet.sEnregistre(utilisateur, pin);
  }
  private void alorsLeGuichetDistribue200Euros(){
  }
  private void leCompteEstAlorsCr�dit�De800Euros(){
  }


}
  
  


  Ajouter dans votre descripteur de pr