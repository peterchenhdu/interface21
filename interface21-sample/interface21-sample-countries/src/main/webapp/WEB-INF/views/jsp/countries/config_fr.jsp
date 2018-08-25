<%@ include file="../common/includes.jsp" %>

<h2><fmt:message key="config.title"/></h2>
<br/>
<p>Vous pouvez utiliser cette d�mo en trois configurations diff�rentes:</p>
<h3>Pr�sentation</h3>
<h4>1. Configuration <strong>simple</strong></h4>
<p>La liste des pays est g�n�r�e en m�moire.</p>
<p>Aucune base de donn�es n'est utilis�e.</p>
<p>C'est la configuration fournie au d�part.</p>
<h4>2. Configuration <strong>copie</strong></h4>
<p>La liste des pays est g�n�r�e en m�moire.</p>
<p>La base de donn�es n'est utilis�e que pour �tre peupl�e par la liste en m�moire.</p>
<p>L'application d�tecte automatiquement cette configuration et propose un choix <strong>copie</strong> sur la page
    d'accueil.</p>
<h4>3. Configuration <strong>base de donn�es</strong></h4>
<p>La liste des pays est lue depuis la base de donn�es.</p>
<p>Il faut avoir test� l'utilisation <strong>copie</strong> et utilis� avec succ�s la fonction de copie dans la base de
    donn�es pour pouvoir utiliser cette configuration.</p>
<br/>
<h3>Technique</h3>
<h4>1. Configuration <strong>simple</strong></h4>
<p>Il n'y a rien � modifier, c'est la configuration fournie.</p>
<h4>2. Configuration <strong>copie</strong></h4>
<p>Dans <strong>countries-servlet.xml</strong>, commentez la partie <strong>ONLY MEMORY OR ONLY DATABASE
    IMPLEMENTATION</strong>. D�commentez la partie <strong>MEMORY+DATABASE IMPLEMENTATION FOR COPYING FROM MEMORY TO
    DATABASE</strong>.</p>
<p>Dans <strong>applicationContext.xml</strong>, commentez la partie <strong>In memory only version</strong>.
    D�commentez la partie <strong>In memory + Database version for copying</strong></p>
<h4>3. Configuration <strong>base de donn�es</strong></h4>
<p>Dans <strong>countries-servlet.xml</strong>, commentez la partie <strong>MEMORY+DATABASE IMPLEMENTATION FOR COPYING
    FROM MEMORY TO DATABASE</strong>. D�commentez la partie <strong>ONLY MEMORY OR ONLY DATABASE IMPLEMENTATION</strong>.
    Vous �tes ainsi revenus � la situation de d�part.</p>
<p>Dans <strong>applicationContext.xml</strong>, commentez la partie <strong>In memory + Database version for
    copying</strong>. D�commentez la partie <strong>Database only version</strong>.</p>
<br/>
