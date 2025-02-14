/**
 * Copyright ou © ou Copr. Ministère de la santé, FRANCE (01/01/2011)
 * dsi-projet.tk@aphp.fr
 *
 * Ce logiciel est un programme informatique servant à la gestion de
 * l'activité de biobanques.
 *
 * Ce logiciel est régi par la licence CeCILL soumise au droit français
 * et respectant les principes de diffusion des logiciels libres. Vous
 * pouvez utiliser, modifier et/ou redistribuer ce programme sous les
 * conditions de la licence CeCILL telle que diffusée par le CEA, le
 * CNRS et l'INRIA sur le site "http://www.cecill.info".
 * En contrepartie de l'accessibilité au code source et des droits de
 * copie, de modification et de redistribution accordés par cette
 * licence, il n'est offert aux utilisateurs qu'une garantie limitée.
 * Pour les mêmes raisons, seule une responsabilité restreinte pèse sur
 * l'auteur du programme, le titulaire des droits patrimoniaux et les
 * concédants successifs.
 *
 * A cet égard  l'attention de l'utilisateur est attirée sur les
 * risques associés au chargement,  à l'utilisation,  à la modification
 * et/ou au  développement et à la reproduction du logiciel par
 * l'utilisateur étant donné sa spécificité de logiciel libre, qui peut
 * le rendre complexe à manipuler et qui le réserve donc à des
 * développeurs et des professionnels  avertis possédant  des
 * connaissances  informatiques approfondies.  Les utilisateurs sont
 * donc invités à charger  et  tester  l'adéquation  du logiciel à leurs
 * besoins dans des conditions permettant d'assurer la sécurité de leurs
 * systèmes et ou de leurs données et, plus généralement, à l'utiliser
 * et l'exploiter dans les mêmes conditions de sécurité.
 *
 * Le fait que vous puissiez accéder à cet en-tête signifie que vous
 * avez pris connaissance de la licence CeCILL, et que vous en avez
 * accepté les termes.
 **/
package fr.aphp.tumorotek.action.comparator;

import java.util.Comparator;

import fr.aphp.tumorotek.action.ManagerLocator;
import fr.aphp.tumorotek.model.cession.Cession;

/**
 * Classe implémentant un comparator sur le nb d'échantillons
 * des cessions.
 *
 * @author Pierre Ventadour.
 * Le 05/07/2011.
 *
 */
public class CessionsNbEchantillonsComparator implements Comparator<Cession>
{

   private Boolean asc;

   public CessionsNbEchantillonsComparator(final Boolean a){
      asc = a;
   }

   @Override
   public int compare(final Cession o1, final Cession o2){
      final Integer i1 = ManagerLocator.getCederObjetManager().getEchantillonsCedesByCessionManager(o1).size();
      final Integer i2 = ManagerLocator.getCederObjetManager().getEchantillonsCedesByCessionManager(o2).size();
      final int v = i1.compareTo(i2);
      return asc ? v : -v;
   }

   public Boolean getAsc(){
      return asc;
   }

   public void setAsc(final Boolean a){
      this.asc = a;
   }

}
