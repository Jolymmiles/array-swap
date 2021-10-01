import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input_data = sc.nextLine();
        String[] spec = input_data.split(" ");
        int n = Integer.parseInt(spec[0]), a = Integer.parseInt(spec[1]), b = Integer.parseInt(spec[2]);
        int[] array = new int[n];
        for (int count = 0; count < n; count += 1){
            array[count] = sc.nextInt();
        }
        if (n < a | n < b | n == 0) {
            System.out.println("NO");
        } else {
            int time_elem = array[a];
            array[a] = array[b];
            array[b] = time_elem;
            for (int elem : array) System.out.println(elem);
        }



    }
}
