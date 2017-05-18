package utils;

import models.*;

/**
 * Utility class containing calculation methods:
 *    Calculate BMI
 *    Determine BMI Category
 *    Is Ideal Body Weight
 *    Convert Height In Metres to Inches
 *    Convert Weight in Kgs to Pounds
 *    Calculate To Two Decimal Places
 *
 * Created by Coady on 17/05/2017.
 */
public class Analytics
{
  /**
   * This method calculates the BMI value for the member.
   * <p>
   * The formula used for BMI is weight divided by the square of the height.
   *
   * @return the BMI value for the member.  The number returned is truncated to two decimal places.
   **/

  public static double calculateBMI(Member member) {
    if (member.assessments.size() > 0) {
      return toTwoDecimalPlaces(member.assessments.get(member.assessments.size() - 1).getWeight() / (member.getHeight() * member.getHeight()));
    } else {
      return toTwoDecimalPlaces(member.getWeight() / (member.getHeight() * member.getHeight()));
    }
  }

  /**
   * This method determines the BMI category that the member belongs to.
   *
   * @return The BMI category that the member belongs to.
   * <p>
   * BMI less than     15(exclusive)   is                     "VERY SEVERLY UNDERWEIGHT".
   * BMI between       15(inclusive)   and 16(exclusive)   is "SEVERELY UNDERWEIGHT".
   * BMI between       16(inclusive)   and 18.5(exclusive) is "UNDERWEIGHT".
   * BMI between       18.5(inclusive) and 25(exclusive)   is "NORMAL".
   * BMI between       25(inclusive)   and 30(exclusive)   is "OVERWEIGHT".
   * BMI between       30(inclusive)   and 35(exclusive)   is "MODERATELY OBESE".
   * BMI between       35(inclusive)   and 40(exclusive    is "SEVERELY OBESE".
   * BMI greater than  40(inclusive)   is                     "VERY SEVERELY OBESE".
   */
  public static String determineBMICategory(double bmiValue) {
    String BMI = "";
    if (bmiValue < 15) {
      BMI = "VERY SEVERELY UNDERWEIGHT";
    } else if ((bmiValue >= 15) && (bmiValue < 16)) {
      BMI = "SEVERELY UNDERWEIGHT";
    } else if ((bmiValue >= 16) && (bmiValue < 18.5)) {
      BMI = "UNDERWEIGHT";
    } else if ((bmiValue >= 18.5) && (bmiValue < 25)) {
      BMI = "NORMAL";
    } else if ((bmiValue >= 25) && (bmiValue < 30)) {
      BMI = "OVERWEIGHT";
    } else if ((bmiValue >= 30) && (bmiValue < 35)) {
      BMI = "MODERATELY OBESE";
    } else if ((bmiValue >= 35) && (bmiValue < 40)) {
      BMI = "SEVERELY OBESE";
    } else if (bmiValue >= 40) {
      BMI = "VERY SEVERELY OBESE";
    }
    return BMI;
  }

  /**
   * This method returns a boolean to indicate if the member has an ideal
   * body weight based on the divine formula
   *
   * For males, an ideal body weight is: 50kg + 2.3kg for each inch over 5 feet
   * For females, an ideal body weight is: 45.5kg + 2.3kg for each inch over 5 feet.
   * Note: If no gender is specified, return result of the female calculation.
   * Note: If the member is 5 feet or less, return 50kg for male and 45.5kg for female.
   *
   * @return Green if the result of the devine formula is within
   * 2kgs (inclusive) of the weight; red if it is outside
   * this range.
   */
  public static String isIdealBodyWeight(Member member)
  {
    double heightInInches = convertHeightMetresToInches(member.getHeight());
    double idealBodyWeight = 0.0;
    double weight;

    if (member.assessments.size() > 0)
    {
      weight = member.assessments.get(member.assessments.size() - 1).getWeight();
    }
    else
    {
      weight = member.getWeight();
    }

    if (member.getHeight() <= 1.52)
    {
      if (member.getGender().equals("Male"))
      {
        idealBodyWeight = 50;
      }
      if (member.getGender().equals("Female"))
      {
        idealBodyWeight = 45.5;
      }
    }

    if (member.getHeight() > 1.52) {
      if (member.getGender().equals("Male"))
      {
        idealBodyWeight = 50 + (2.3 * (heightInInches - 60));
      }
      if (member.getGender().equals("Female"))
      {
        idealBodyWeight = 45.5 + (2.3 * (heightInInches - 60));
      }

    }

    if ((weight >= (idealBodyWeight - 2)) && (weight <= (idealBodyWeight + 2)))
    {
      return "green";
    }
    else
    {
      return "red";
    }
  }

























  public static String idealWeight(String bmiCategory)
  {
    String colour;

    if(bmiCategory.contains("SEVERELY") || bmiCategory.contains("OBESE"))
    {
      colour = "red";
    }
    else if(bmiCategory.contains("UNDERWEIGHT") || bmiCategory.contains("OVERWEIGHT"))
    {
      colour = "orange";
    }
    else
    {
      colour = "green";
    }
    return colour;
  }

  /**
   * Returns the member height converted from metres to inches.
   *
   * @param height Members height.
   *
   * @return The member height converted from metres to inches using the formula:
   * metres multiplied by 39.37. The number returned is truncated to two decimal places.
   */

  public static double convertHeightMetresToInches(double height)
  {
    return toTwoDecimalPlaces(height * 39.37);
  }

  /**
   * Returns the member weight converted from kgs to pounds.
   *
   * @param weight Members weight.
   *
   * @return The member weight converted from kgs to pounds using the formula:
   * kg multiplied by 2.20. The number is truncated to two decimal places.
   */

  public static double convertWeightKgToPounds(double weight)
  {
    return toTwoDecimalPlaces(weight * 2.20);
  }

  /**
   * Returns to two decimal places.
   *
   * @param num
   *
   * @return Value to decimal places.
   */

  public static double toTwoDecimalPlaces(double num)
  {
    return (int)(num * 100) / 100.0;
  }
}
