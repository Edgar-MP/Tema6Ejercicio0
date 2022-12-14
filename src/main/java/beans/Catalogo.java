package beans;

public class Catalogo {

  private static String[] libros = new String[] {
    "Star Wars: Desde Otro Punto de Vista",
    "Guerra y paz",
    "Por si las voces vuelven",
    "El imperio final",
    "Fuego y Sangre",
  };

  public static String[] getNombres() {
    return libros;
  }
}
