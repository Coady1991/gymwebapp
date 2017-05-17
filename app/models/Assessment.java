package models;

import javax.persistence.Entity;


import play.db.jpa.Model;

/**
 * Created by Coady on 25/04/2017.
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


  public Assessment(double weight, double chest, double thigh, double upperArm, double waist, double hips)
  {
    this.weight = weight;
    this.chest = chest;
    this.thigh = thigh;
    this.upperArm = upperArm;
    this.waist = waist;
    this.hips = hips;
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

  /**
   * Returns the chest size for the Assessment.
   *
   * @return The chest size for the Assessment.
   */
  public double getChest()
  {
    return chest;
  }

  /**
   * Returns the thigh size for the Assessment.
   *
   * @return The thigh size for the Assessment.
   */
  public double getThigh()
  {
    return thigh;
  }

  /**
   * Returns the upper arm size for the Assessment.
   *
   * @return The upper arm size for the Assessment.
   */
  public double getUpperArm()
  {
    return upperArm;
  }

  /**
   * Returns the waist size for the Assessment.
   *
   * @return The waist size for the Assessment.
   */
  public double getWaist()
  {
    return waist;
  }

  /**
   * Returns the hip size for the Assessment.
   *
   * @return The hip size for the Assessment.
   */
  public double getHips()
  {
    return hips;
  }
}