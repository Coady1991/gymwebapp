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
}