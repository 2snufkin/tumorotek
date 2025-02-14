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
package fr.aphp.tumorotek.model.stats;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * Objet persistant mappant la table STATS_MODELE_INDICATEUR.
 * Classe créée le .
 * @see http://boris.kirzner.info/blog/archives/2008/07/19/*%20
 * 		hibernate-annotations-the-many-to-many-association-with-composite-key/
 *
 * @author
 * @version 2.0
 *
 */
@Entity
@Table(name = "STATS_MODELE_INDICATEUR")
@AssociationOverrides({
   @AssociationOverride(name = "pk.sModele",
      joinColumns = @JoinColumn(name = "STATS_MODELE_ID", referencedColumnName = "STATS_MODELE_ID")),
   @AssociationOverride(name = "pk.indicateur",
      joinColumns = @JoinColumn(name = "STATS_INDICATEUR_ID", referencedColumnName = "STATS_INDICATEUR_ID"))})
//@AttributeOverride(column =
//	@Column(name = "ORDRE"), name = "ordre")
public class SModeleIndicateur implements Serializable
{

   private static final long serialVersionUID = 1L;

   private Integer ordre;
   private SModeleIndicateurPK pk = new SModeleIndicateurPK();

   /** Constructeur par défaut. */
   public SModeleIndicateur(){}

   public SModeleIndicateur(final SModele sM, final Indicateur i, final Integer o){
      pk.setIndicateur(i);
      pk.setsModele(sM);
      setOrdre(o);
   }

   @EmbeddedId
   @AttributeOverrides({@AttributeOverride(name = "sModele", column = @Column(name = "STATS_MODELE_ID")),
      @AttributeOverride(name = "indicateur", column = @Column(name = "STATS_INDICATEUR_ID"))})
   public SModeleIndicateurPK getPk(){
      return pk;
   }

   public void setPk(final SModeleIndicateurPK pmk){
      this.pk = pmk;
   }

   @Column(name = "ORDRE", nullable = false)
   public Integer getOrdre(){
      return this.ordre;
   }

   public void setOrdre(final Integer o){
      this.ordre = o;
   }

   @Transient
   public SModele getSModele(){
      return this.pk.getsModele();
   }

   public void setSModele(final SModele sm){
      this.pk.setsModele(sm);
   }

   @Transient
   public Indicateur getIndicateur(){
      return this.pk.getIndicateur();
   }

   public void setIndicateur(final Indicateur i){
      this.pk.setIndicateur(i);
   }

   /**
    * 2 liens sont considérés comme égaux s'ils ont la même pk.
    * @param obj est le lien à tester.
    * @return true si les liens sont égaux.
    */
   @Override
   public boolean equals(final Object obj){

      if(this == obj){
         return true;
      }
      if((obj == null) || obj.getClass() != this.getClass()){
         return false;
      }
      final SModeleIndicateur test = (SModeleIndicateur) obj;
      return (this.pk != null && (this.pk == test.pk || this.pk.equals(test.pk)));
   }

   /**
    * Le hashcode est calculé sur la pk.
    * @return la valeur du hashcode.
    */
   @Override
   public int hashCode(){

      int hash = 7;
      int hashPk = 0;

      if(this.pk != null){
         hashPk = this.pk.hashCode();
      }

      hash = 7 * hash + hashPk;

      return hash;
   }
}
