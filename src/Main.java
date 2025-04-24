import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

interface Operacion {
    String calcular();
}

abstract class OperacionFigura implements Operacion {
    protected double[] valores;

    public OperacionFigura(double... valores) {
        this.valores = valores;
    }
}

class AreaCirculo extends OperacionFigura {
    public AreaCirculo(double radio) {
        super(radio);
    }
    public String calcular() {
        double area = Math.PI * valores[0] * valores[0];
        return String.format("Área de Círculo (radio=%.2f): %.2f", valores[0], area);
    }
}

class PerimetroCirculo extends OperacionFigura {
    public PerimetroCirculo(double radio) {
        super(radio);
    }
    public String calcular() {
        double perimetro = 2 * Math.PI * valores[0];
        return String.format("Perímetro de Círculo (radio=%.2f): %.2f", valores[0], perimetro);
    }
}

class AreaCuadrado extends OperacionFigura {
    public AreaCuadrado(double lado) {
        super(lado);
    }
    public String calcular() {
        double area = valores[0] * valores[0];
        return String.format("Área de Cuadrado (lado=%.2f): %.2f", valores[0], area);
    }
}

class PerimetroCuadrado extends OperacionFigura {
    public PerimetroCuadrado(double lado) {
        super(lado);
    }
    public String calcular() {
        double perimetro = 4 * valores[0];
        return String.format("Perímetro de Cuadrado (lado=%.2f): %.2f", valores[0], perimetro);
    }
}

class AreaTriangulo extends OperacionFigura {
    public AreaTriangulo(double base, double altura) {
        super(base, altura);
    }
    public String calcular() {
        double area = (valores[0] * valores[1]) / 2;
        return String.format("Área de Triángulo (base=%.2f, altura=%.2f): %.2f", valores[0], valores[1], area);
    }
}

class PerimetroTriangulo extends OperacionFigura {
    public PerimetroTriangulo(double lado) {
        super(lado);
    }
    public String calcular() {
        double perimetro = 3 * valores[0];
        return String.format("Perímetro de Triángulo (lado=%.2f): %.2f", valores[0], perimetro);
    }
}

class AreaRectangulo extends OperacionFigura {
    public AreaRectangulo(double base, double altura) {
        super(base, altura);
    }
    public String calcular() {
        double area = valores[0] * valores[1];
        return String.format("Área de Rectángulo (base=%.2f, altura=%.2f): %.2f", valores[0], valores[1], area);
    }
}

class PerimetroRectangulo extends OperacionFigura {
    public PerimetroRectangulo(double base, double altura) {
        super(base, altura);
    }
    public String calcular() {
        double perimetro = 2 * (valores[0] + valores[1]);
        return String.format("Perímetro de Rectángulo (base=%.2f, altura=%.2f): %.2f", valores[0], valores[1], perimetro);
    }
}

class AreaPentagono extends OperacionFigura {
    public AreaPentagono(double lado, double apotema) {
        super(lado, apotema);
    }
    public String calcular() {
        double area = (5 * valores[0] * valores[1]) / 2;
        return String.format("Área de Pentágono (lado=%.2f, apotema=%.2f): %.2f", valores[0], valores[1], area);
    }
}

class PerimetroPentagono extends OperacionFigura {
    public PerimetroPentagono(double lado) {
        super(lado);
    }
    public String calcular() {
        double perimetro = 5 * valores[0];
        return String.format("Perímetro de Pentágono (lado=%.2f): %.2f", valores[0], perimetro);
    }
}

class Potencia implements Operacion {
    private double base;
    private int exponente;

    public Potencia(double base, int exponente) {
        this.base = base;
        this.exponente = exponente;
    }

    public String calcular() {
        double resultado = calcularRecursivo(base, exponente);
        return String.format("%.2f ^ %d = %.2f", base, exponente, resultado);
    }

    private double calcularRecursivo(double base, int exponente) {
        if (exponente == 0) return 1;
        if (exponente < 0) return 1 / calcularRecursivo(base, -exponente);
        return base * calcularRecursivo(base, exponente - 1);
    }
}

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<String> historial = new ArrayList<>();

    public static void main(String[] args) {
        new Main().iniciar();
    }

    public void iniciar() {
        boolean continuar = true;

        while (continuar) {
            try {
                System.out.println("CALCULADORA ");
                System.out.println("1. Área");
                System.out.println("2. Perímetro");
                System.out.println("3. Potencia");
                System.out.println("4. Ver historial");
                System.out.println("5. Salir");
                System.out.print("Elige opción: ");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1: calcularArea(); break;
                    case 2: calcularPerimetro(); break;
                    case 3: calcularPotencia(); break;
                    case 4: mostrarHistorial(); break;
                    case 5: continuar = false; break;
                    default: System.out.println("Opción inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida.");
                scanner.nextLine();
            }
        }
    }

    private void calcularArea() {
        System.out.println("Figuras:");
        System.out.println("1. Círculo");
        System.out.println("2. Cuadrado");
        System.out.println("3. Triángulo");
        System.out.println("4. Rectángulo");
        System.out.println("5. Pentágono");
        System.out.print("Elige figura: ");
        int figura = scanner.nextInt();

        Operacion operacion = null;

        switch (figura) {
            case 1:
                System.out.print("Radio: ");
                operacion = new AreaCirculo(scanner.nextDouble());
                break;
            case 2:
                System.out.print("Lado: ");
                operacion = new AreaCuadrado(scanner.nextDouble());
                break;
            case 3:
                System.out.print("Base: ");
                double base = scanner.nextDouble();
                System.out.print("Altura: ");
                double altura = scanner.nextDouble();
                operacion = new AreaTriangulo(base, altura);
                break;
            case 4:
                System.out.print("Base: ");
                base = scanner.nextDouble();
                System.out.print("Altura: ");
                altura = scanner.nextDouble();
                operacion = new AreaRectangulo(base, altura);
                break;
            case 5:
                System.out.print("Lado: ");
                double lado = scanner.nextDouble();
                System.out.print("Apotema: ");
                double apotema = scanner.nextDouble();
                operacion = new AreaPentagono(lado, apotema);
                break;
            default:
                System.out.println("Opción inválida.");
                return;
        }

        String resultado = operacion.calcular();
        historial.add(resultado);
        System.out.println(resultado);
    }

    private void calcularPerimetro() {
        System.out.println("Figuras:");
        System.out.println("1. Círculo");
        System.out.println("2. Cuadrado");
        System.out.println("3. Triángulo");
        System.out.println("4. Rectángulo");
        System.out.println("5. Pentágono");
        System.out.print("Elige figura: ");
        int figura = scanner.nextInt();

        Operacion operacion = null;

        switch (figura) {
            case 1:
                System.out.print("Radio: ");
                operacion = new PerimetroCirculo(scanner.nextDouble());
                break;
            case 2:
                System.out.print("Lado: ");
                operacion = new PerimetroCuadrado(scanner.nextDouble());
                break;
            case 3:
                System.out.print("Lado: ");
                operacion = new PerimetroTriangulo(scanner.nextDouble());
                break;
            case 4:
                System.out.print("Base: ");
                double base = scanner.nextDouble();
                System.out.print("Altura: ");
                double altura = scanner.nextDouble();
                operacion = new PerimetroRectangulo(base, altura);
                break;
            case 5:
                System.out.print("Lado: ");
                operacion = new PerimetroPentagono(scanner.nextDouble());
                break;
            default:
                System.out.println("Opción inválida.");
                return;
        }

        String resultado = operacion.calcular();
        historial.add(resultado);
        System.out.println(resultado);
    }

    private void calcularPotencia() {
        System.out.print("Base: ");
        double base = scanner.nextDouble();
        System.out.print("Exponente: ");
        int exponente = scanner.nextInt();

        Operacion operacion = new Potencia(base, exponente);
        String resultado = operacion.calcular();
        historial.add(resultado);
        System.out.println(resultado);
    }

    private void mostrarHistorial() {
        if (historial.isEmpty()) {
            System.out.println("Sin historial.");
        } else {
            for (String res : historial) {
                System.out.println(res);
            }
        }
    }
}
