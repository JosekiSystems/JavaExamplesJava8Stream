import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProyectoJava8Stream {
    public static void main(String[] args) {

        //1
        System.out.println(" ==== Sumatoria (Array - % 10 = 0)/2 === ");

        int[] arreglo = new int[100];

        for (int i = 0; i < arreglo.length; i++) {
            arreglo[i] = i + 1;
        }

        double total = Arrays.stream(arreglo)
                .filter(e -> e % 10 != 0)
                .mapToDouble(e -> (double) e / 2)
                .reduce(0, (ac, e) -> ac + e);
        System.out.println("total = " + total);

        //2
        System.out.println(" ==== Function Max=== ");

        Function<Integer[],Integer> max = arr ->
                Arrays.stream(arr).reduce(0,(ac,e) -> ac > e? ac: e);

        int resultado = max.apply(new Integer[]{1,78,2500,0,54,2,5000});
        System.out.println("MAX = " + resultado);

        //3
        System.out.println(" ==== Filter Sobre Arrays === ");

        String[][] lenguajes = {{"java", "groovy"}, {"php"}, {"c#", "python", "groovy"}, {"java", "javascript", "kotlin"}, {"javascript"}, {}};
        Arrays.stream(lenguajes)
                .flatMap(arr -> Arrays.stream(arr))
                .distinct()
                .forEach(e -> System.out.println(e));

        //4
        System.out.println(" ==== Function flatMap sonre Arrays=== ");

        String[][] lenguajes2 = {{"java", "groovy"}, {"php"}, {"c#", "python", "groovy"}, {"java", "javascript", "kotlin"}, {"javascript"}, {}};

        Function<String[][], List<String>> flatten = arr ->  Arrays.stream(arr).flatMap(a -> Arrays.stream(a))
                .distinct()
                .collect(Collectors.toList());

        flatten.apply(lenguajes2).forEach(e -> System.out.println(e));

        //5
        System.out.println(" ==== 1 Sumatoria List con map === ");
        List<Producto> facturas = Arrays.asList(new Producto(9.99, 1), new Producto(19.99, 1.5), new Producto(4.99, 2));
        double sum = facturas.stream()
                .map(p -> p.getCantidad() * p.getPrecio())
                .reduce(0d, Double::sum);
        System.out.println(sum);

        //2
        System.out.println(" ==== 2 Sumatoria List mapToDouble === ");
        List<Producto> facturas2 = Arrays.asList(new Producto(9.99, 1), new Producto(19.99, 1.5), new Producto(4.99, 2));

        double sum2 = facturas2.stream()
                .mapToDouble(x -> x.getCantidad() * x.getPrecio())
                .sum();

        System.out.println(sum2);

    }
}

/*
1.-
Consiste en un arreglo de 100 elementos del 1 al 100 del tipo int,
utilizando el api stream se pide eliminar los divisibles en 10,
luego convertir los elementos restante del flujo en tipo double y dividirlos en 2,
para finalmente devolver la suma total de todos ellos usando el operador terminal reduce.
2.-
Obtener el numero mayor en un arreglo con programación funcional lambda y api stream
3.-
Aplanar un arreglo bidimensional en un nivel y eliminar repetidos usando el api stream.
4.-
Aplanar un arreglo bidimensional en un nivel y eliminar repetidos usando el api Arrays.stream.
5.-
Lista de productos List<Producto> , de 3 a 5 elementos,
se pide calcular el importe (precio por cantidad) y sumarlos.
La clase Producto debe tener el atributo precio del tipo double y cantidad int,
entonces utilizando stream convertir la lista de productos en el gran total del tipo double.
 */


