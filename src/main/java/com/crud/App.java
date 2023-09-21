package com.crud;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.crud.model.Student;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Project Started " );
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory= cfg.buildSessionFactory();
        
        
        
        Scanner sc= new Scanner(System.in);
        int ch;
        
        do
    	{
    		System.out.println("____________________*****-----*****______________________");
    		System.out.println("1. Save the student ");
    		System.out.println("2. Update the student");
    		System.out.println("3. Display the student data");
    		System.out.println("4. display all student data ");
    		System.out.println("5. delete the student data ");
    		System.out.println("6. exit ");
    		
    		System.out.println("____________________*****-----*****______________________");
    		
    		
    		System.out.println("enter your choice ...?");
    		ch=sc.nextInt();
    		String fname;
    		String lname;
    		String email;
    		long id;
    	
    		switch(ch)
    		{
    		
    		case 1: 
    			System.out.println("enter the student id:");
    			id=sc.nextLong();
    			
    			System.out.println("enter the student first name ");
    			fname=sc.next();
    			
    			System.out.println("enter the student last name ");
    			lname=sc.next();
    			
    			System.out.println("enter the student email");
    			email=sc.next();
    			Student st = new Student();
    			st.setId(id);
    			st.setFirstName(fname);
    			st.setLastName(lname);
    			st.setEmail(email);
    			
    			Session session =factory.openSession();
    			Transaction tx= session.beginTransaction();
    			session.save(st);
    					
    			tx.commit();
    			session.close();
    			System.out.println("Student data inserted successfully !");
    			
    			break;
    			
    			
    		case 2:
    			
    			System.out.println("enter the Student id whose data you wants to update :");
    			id=sc.nextLong();
    			
    			
    			System.out.println("enter the new First name ");
    			fname=sc.next();
    			
    			System.out.println("enter the new Last name ");
    			lname=sc.next();
    			
    			System.out.println("enter the new email");
    			email=sc.next();
    			
    			Session session2 =factory.openSession();
    			Transaction tx2= session2.beginTransaction();
    			
    			Student student = session2.get(Student.class, id);
    			student.setFirstName(fname);
    			student.setLastName(lname);
    			student.setEmail(email);
    			
    			session2.saveOrUpdate(student);
    			
    			tx2.commit();
    			session2.close();
    			System.out.println("Student data updated  successfully !");
    			
    			
    			
    			break;
    			
    		case 3:
    			System.out.println("enter the student id whose data you wants to display");
    			id=sc.nextLong();
    			
    			Session session3 =factory.openSession();
    			Transaction tx3= session3.beginTransaction();
    			
    			Student student2 = session3.get(Student.class, id);
    			
    			System.out.println("ID :"+student2.getId());
    			System.out.println("First name :"+student2.getFirstName());
    			System.out.println("Last name :"+student2.getLastName());
    			System.out.println("email :"+student2.getEmail());
    			
    			tx3.commit();
    			session3.close();
    			
    			break;
    			
    		case 4:
    			
    			Session session4 =factory.openSession();
    			Transaction tx4= session4.beginTransaction();
    			
    			List<Student> students = (List<Student>) session4.createQuery("from Student").list();
    			for(Student s3: students)
    			{
    				System.out.println("ID :"+s3.getId());
        			System.out.println("First name :"+s3.getFirstName());
        			System.out.println("Last name :"+s3.getLastName());
        			System.out.println("email :"+s3.getEmail());
    			}
    			break;
    			
    		case 5:
    			
    			System.out.println("enter the student id whose data you wanted to delete .");
    			id=sc.nextLong();
    			
    			Session session5 =factory.openSession();
    			Transaction tx5= session5.beginTransaction();
    			
    			Student s5= session5.get(Student.class, id);
    			session5.delete(s5);
    			
    			System.out.println("Student record deleted successfully !!");
    			
    			tx5.commit();
    			session5.close();
    			
    			break;
    			
    			
    		case 6:
    			System.exit(0);
    			
    			
    			
    		}
    		
    		
    			
    		
    	}
    	while(ch!=9);
        
        
        factory.close();
        
    }
}
