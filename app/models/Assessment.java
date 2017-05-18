package models;

import javax.persistence.Entity;


import play.db.jpa.Model;

/**
 * The Assessment class contains information on parameters needed to create an assessment.
 */
@Entity
public class Assessment extends Model
{
  public double weight;
  public double chest;
  public double thigh;
  public double upperArm;
  public double waist;
  public double hips;
  public String comment;

  /**
   * The parameters for an assessment.
   *
   * @param weight The members weight.
   * @param chest The members chest size.
   * @param thigh The members thigh size.
   * @param upperArm The members upper arm size.
   * @param waist The members waist size.
   * @param hips The members hip size.
   * @param comment The comment for the assessment.
   */
  public Assessment(double weight, double chest, double thigh, double upperArm, double waist, double hips, String comment)
  {
    this.weight = weight;
    this.chest = chest;
    this.thigh = thigh;
    this.upperArm = upperArm;
    this.waist = waist;
    this.hips = hips;
    this.comment = comment;
  }

  //***************************************************************************************
  // Getters for Assessment
  //***************************************************************************************

  /**
   * Returns the weight for the Assessment.
   *
   * @return The weight for the Assessment
   */
  public double getWeight()
  {
    return weight;
  }

  //***************************************************************************************
  // Getters for Assessment
  //***************************************************************************************

  /**
   * Updates the comment for the Assessment.
   *
   * @param comment The assessments comment.
   */
  public void setComment(String comment)
  {
    this.comment = comment;
  }
}