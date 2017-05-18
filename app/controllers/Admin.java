package controllers;

import java.util.List;

import models.*;
import play.mvc.Controller;

public class Admin extends Controller
{
  public static void index()
  {
    List<Assessment> member = Assessment.findAll();
    render("admin.html", member);
  }
}