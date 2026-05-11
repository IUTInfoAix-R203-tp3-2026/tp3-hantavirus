package fr.univ_amu.iut.exercice3;

/// Employé simple pour le kata 3.
///
/// Un `record` convient parfaitement : un employé est une **valeur** (deux employés avec les mêmes
/// nom et âge sont "le même employé" pour nos tests).
public record Employe(String nom, int age) {}
