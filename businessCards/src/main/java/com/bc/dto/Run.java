package com.bc.dto;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.sun.glass.ui.Pixels.Format;

public class Run
{
    public static final Scanner SC = new Scanner(System.in);
    private String order;
    private String answer;
    
    BcDAO dao= BcDAO.getDao();
    
    
    public String getOrder()
    {
        return order;
    }
    
    public void setOrder(String order)
    {
        this.order = order;
    }
    
    
    public String getAnswer()
    {
        return answer;
    }
    
    public void setAnswer(String answer)
    {
        this.answer = answer;
    }
    
    
    
    public void greeting()
    {
        System.out.println("반갑습니다.");
        System.out.println("1:명함입력  2:전체명함보기  3:명함검색  4:프로그램 종료");
        System.out.println("전체명함보기 및 검색->수정,삭제");
        
        do
        {
            try
            {
                order = SC.next();
                break;
            }
            catch (InputMismatchException e)
            {
                e.printStackTrace();
            }    
            System.out.println("1에서  4 사이의 숫자를 입력해주세요");
            SC.nextLine();
        }
        while(true);
    }
    
    public void proceedAddOrInsert(BusinessCards bc, String work)
    {
        
            while(true)
            {
                System.out.println("이름: ");
                bc.setName(SC.next());
                System.out.println("전화번호: ");
                bc.setTel(SC.next());
                System.out.println("이메일: ");
                bc.setEmail(SC.next());
                
                System.out.println(bc.toString());
                System.out.println("이대로 진행하시겠습니까? y/n");
                
                order= SC.next();
                
                if(order.equals("y"))
                {
                    if(work.equals("입력"))
                    {
                        dao.addCard(bc);
                    }
                    else
                    {
                        dao.updateCard(bc);                       
                    }
                    System.out.println(work+"했습니다.");
                    return;
                }
                else
                {
                    System.out.println("다시 입력: y 메뉴로 돌아가기: n");
                    order= SC.next();
                    
                    if(order.equals("y"))
                    {
                        continue;
                    }
                    else
                    {
                        System.out.println("취소합니다.");
                        return;
                    }
                }   
            }
            
            
            
        }
     
    
    
    
    public void insert()
    {
        
        System.out.println("----------명함입력----------");
        
        BusinessCards bc = new BusinessCards();    
        
        BusinessCards.setSerial(dao.getMaxNum());        
        bc.setNum(bc.getSerial());
        
        proceedAddOrInsert(bc, "입력");
     }
    
    
    public void allList()
    {
        System.out.println("----------전체 리스트----------");
        List<BusinessCards> arr= dao.viewAllCards();
        int i=0;
        
        if(arr.isEmpty())
        {
            System.out.println("데이터가 없습니다.");
        }
        
        else
        {
            if(arr.size()>=10)
            {
                int count= arr.size();
                
                System.out.println(String.format("총 %d건의 결과가 있습니다.", count));
                System.out.println("몇 건을 검색하시겠습니까?");
                
                while(true)
                {
                    try
                    {
                        i = SC.nextInt();
                        
                        if(i>=count)
                        {
                            i=count;  
                        }
                        break;
                        
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    SC.nextLine();
                    System.out.println("알맞은 값을 입력해주세요.");
                }
                
                for(int a=0; a<i; a++)
                {
                    System.out.println(arr.get(a).toString());
                }
            }
            
            else
            {
                for(BusinessCards bc : arr)
                {
                    System.out.println(bc.toString());
                }  
            }
            
            choiceUpdateOrDel();
            
            
            
        }
        
    }
    
           
    
    public int proceedUpdateOrDel(String param)
    {
        int num=0;
        
       
        System.out.println("----------명함 "+param+"----------");
        System.out.println("어떤 명함을 "+param+"하시겠습니까? 번호를 입력해주세요.");
        
        while(true)
        {
            try
            {
                num=SC.nextInt();
                break;
            }
            catch (InputMismatchException e)
            {
                System.out.println("숫자를 입력해주세요");
                e.printStackTrace();
            }
            catch(NoSuchElementException e)
            {
                e.printStackTrace();
            }
            
            SC.nextLine();
        }
        
        return num;
    }
    
    
    public void choiceUpdateOrDel()
    {
        System.out.println("수정:u 삭제:d 메뉴로:n ");
        order= SC.next();
        if(order.equals("u"))
        {
          update();  
        }
        else if(order.equals("d"))
        {
            delete();
        }
        
    }
    
    public void update()
    {
        int num = proceedUpdateOrDel("수정");
        
        BusinessCards bc = dao.searchCard(num);
        
        
        if(bc!=null)
        {
            System.out.println(bc.toString());
            proceedAddOrInsert(bc, "수정");
        }
        else
        {
            System.out.println("없는 번호입니다.");
        }
            
    }
    
        
        public void delete()
        {
            int num = proceedUpdateOrDel("삭제");
            
            
            BusinessCards bc =dao.searchCard(num);
            if(bc!=null)
            {
                System.out.println(bc.toString());
                System.out.println("삭제하시겠습니까? y/n");
                order=SC.next();
                
                if(order.equals("y"))
                {
                    dao.delCard(num);
                    System.out.println("삭제했습니다.");             
                }
                else
                {
                    System.out.println("취소합니다.");
                }
            }
            
            else
            {
                System.out.println("없는 번호입니다.");
            }
            
        }
        
        
        
        
        public void search()
        {
            //이름, 전화번호, 이메일 선택해서 찾을 수 있도록?
            List<BusinessCards> arr = null;
            String searchBy="";
            String contents="";
            
            while(true)
            {
                System.out.println("무엇으로 검색할까요? 이름:n 전화번호:t 메일:m");
                System.out.println("부분적인 검색어로도 검색이 가능합니다.");
                
                order = SC.next();
                
                if(order.equals("n"))
                {
                  searchBy= "name";
                  System.out.print("이름");
                    
                }
                else if(order.equals("t"))
                {
                    searchBy= "tel";
                    System.out.print("전화번호");
                }
                else if(order.equals("m"))
                {
                    searchBy="email";
                    System.out.print("메일");
                }
                
                else
                {
                    System.out.println("값을 다시 입력해주세요.");
                    continue;
                }
                
                System.out.println("로 검색합니다.");
                contents= SC.next();
                
                arr= dao.searchCard(searchBy, contents);
                
                if(arr.isEmpty())
                {
                    System.out.println("검색 결과가 없습니다.");
                    System.out.println("다시 검색하시겠습니까? y/n");
                    
                    answer= SC.next();
                    
                    if(answer.equals("y")) 
                    {
                        continue; 
                    }   
                    
                    return;
                }
                else
                {
                    for(BusinessCards bc : arr)
                    {
                        System.out.println(bc.toString());
                    }  
                    break;
                }    
            }
            choiceUpdateOrDel();
        }
        
        
        public static void main(String[] args)
        {
            
            Run run = new Run();
            
            while(true)
            {
                run.greeting();   
                
                switch(run.getOrder())
                {
                    case "1": run.insert();
                    break;
                    case "2": run.allList();
                    break;
                    case "3": run.search();
                    break;
                    case "4": 
                        System.out.println("종료합니다. 이용해주셔서 감사합니다😊");
                        SC.close();
                        return; 
                    default: System.out.println("알맞은 값을 입력해주세요.");
                }    
            }
            
            
        }
                
    }
    
