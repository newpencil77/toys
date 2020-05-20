import java.util.Scanner;

public class UpdownNumberQuiz {

//    나는 간단하게 숫자 맞추기 게임을 만들고 싶다.1과 100사의 수 중에서 무작위로 선택되어야하고, 플레이어는 10번의 기회안에 그 숫자를 맞춰야 한다.
//    각 순서마다 숫자를 맞춰는지 틀렸는지는 당연히 알려줘야 하고, 틀렸다면 큰지 작은지도 포함해서 말해야 한다.또한 이전에 써냈던 번호들도 보여줘야 한다.
//    게임은 플레이어가 숫자를 맞추던가, 기회를 모두 소진하면 끝나게 된다. 게임이 끝나면 플레이어가 다시 게임을 할 것인지 묻게된다.


    //난수 발생 1~100. 정답으로 변수에 저장
    //플레이어에게서 값 입력받기 //정답과 비교, 정답인지 큰지 작은지 알려줌
    //이전의 값들을 보여줌
    //게임오버는 플레이어가 숫자를 맞히든가, 기회를 모두 소진하든가.




    public static void main(String[] args) {
        int maxNum= 100;
        int minNum= 1;
        int answer= (int)Math.floor(Math.random()*(maxNum-minNum)+minNum);
        int chance= 10;
//        System.out.println(answer);

        int[] tryNumber = new int[chance];

        System.out.println("업다운 숫자 맞히기 게임!");
        System.out.println(minNum+ "부터 "+ maxNum+"까지의 숫자를 입력해주세요. 기회는 "+ chance+"번입니다");


        for(int i=0; i<chance;)
        {
            Scanner sc= new Scanner(System.in);
            int playerChoice= sc.nextInt();

            if(playerChoice== answer)
            {
            System.out.println("훌륭합니다! 정답을 맞혔습니다.");
            sc.close();
            break;
            }

            else
            {
                if(playerChoice> answer)
                {
                    System.out.println("정답은 그보다 더 작습니다.");
                }
                else
                {
                    System.out.println("정답은 그보다 더 큽니다.");
                }

                tryNumber[i] = playerChoice;
                for(int j= 0; j<=i; j++)
                {
                    System.out.print(tryNumber[j] + " ");
                }
                i++;

                if(i ==10)
                {
                    System.out.println("\n안타깝지만 기회를 모두 소진했습니다. 정답은 "+ answer+ "입니다.");
                    sc.close();
                }
            }

        }

    }
}
