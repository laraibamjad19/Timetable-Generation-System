 package javaframes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import javax.swing.JOptionPane;

public class Timetable {
    
    private String title="";
    private ArrayList <TimeSlot> slots=new ArrayList<TimeSlot>();
    private ArrayList <Course> courses=new ArrayList<Course>();
    private ArrayList <Department> departments=new ArrayList<Department>();
    private ArrayList <Instructor> instructors=new ArrayList<Instructor>();
    private ArrayList <User> users=new ArrayList<User>();
    private ArrayList <Section> sections=new ArrayList<Section>();
    private ArrayList <Batch> batches=new ArrayList<Batch>();
    
    Connection timtableCon;
    Statement timetablestmt;
    
    public Timetable(){
        
       try{
        String connection = "jdbc:sqlserver://localhost:1433;databaseName=project"; //check
        this.timtableCon= (Connection) DriverManager.getConnection(connection,"ramin_rafi","12569");
        this.timetablestmt = (Statement) this.timtableCon.createStatement(); 
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void Timetable(String t, ArrayList<TimeSlot> sl, ArrayList<Course> c, ArrayList<Batch> b,
            ArrayList<Department> d, ArrayList <Instructor> i, ArrayList <Section> sec){
        this.title= t;
        this.slots=sl;
        this.courses=c;
        this.batches=b;
        this.departments=d;
        this.instructors=i;
        this.sections=sec;
    }
            
    public void setTitle(String s){
        title=s;
    }
    
    public void setTimeSlotsUsingDatabase(){
        
         try{         
            String connection = "jdbc:sqlserver://localhost:1433;databaseName=project"; //check
            this.timtableCon= (Connection) DriverManager.getConnection(connection,"ramin_rafi","12569");
            this.timetablestmt = (Statement) this.timtableCon.createStatement(); 
            try{
                String query = "select * from TimeSlot";

                PreparedStatement preparedStmt = this.timtableCon.prepareStatement(query);

                ResultSet rs = preparedStmt.executeQuery();
                
                while(rs.next()){
                    TimeSlot tempTimeSlot = new TimeSlot();
                    tempTimeSlot.setTimeSlotDetailsUsingDatabase(rs.getString(5),rs.getString(3));
                    this.slots.add(tempTimeSlot);
                }       
            }
            catch(Exception e)
            {
                System.out.println(e);
            } 
        }
        catch(Exception e)
        {
            System.out.println(e);
        }    
    }
    
    public void Schedule()
    {
        try{  
            int numberOfSections=0,i=0;
            String connection = "jdbc:sqlserver://localhost:1433;databaseName=project"; //check
            this.timtableCon= (Connection) DriverManager.getConnection(connection,"ramin_rafi","12569");
            this.timetablestmt = (Statement) this.timtableCon.createStatement(); 
            try{
                String query3 = "select * from Course";
                PreparedStatement preparedStmt3 = this.timtableCon.prepareStatement(query3);
                ResultSet rs3 = preparedStmt3.executeQuery();
                while(rs3.next()){
                    
                   numberOfSections=numberOfSections+rs3.getInt(7);
                }   
               
                while(numberOfSections>0)
                {   
                    System.out.println("Updating TimeSlots");
                    
                    String query2="UPDATE TimeSlot SET isAvailable = ?, courseID = ?, Section = ? where id = ? ";
                    PreparedStatement preparedStmt2 = this.timtableCon.prepareStatement(query2);
                    preparedStmt2.setString(1,"false");
                    System.out.println("CourseID = "+this.sections.get(i).getCourse()+" Section = "+this.sections.get(i).getName());
                    preparedStmt2.setString(2,this.sections.get(i).getCourse());
                    preparedStmt2.setString(3,this.sections.get(i).getName());
                   
                    preparedStmt2.setInt(4,i+1);
                    preparedStmt2.executeUpdate();   
                    numberOfSections--;
                    i++;
                }
                System.out.println("TimeSlots have been updated. " );
            }
            catch(Exception e)
            {
                System.out.println(e);
            }  
        }
        catch(Exception e)
        {
            System.out.println(e);
        }        

    }

   
    public void setCoursesUsingDatabase(){
         try{         
            String connection = "jdbc:sqlserver://localhost:1433;databaseName=project"; //check
            this.timtableCon= (Connection) DriverManager.getConnection(connection,"ramin_rafi","12569");
            this.timetablestmt = (Statement) this.timtableCon.createStatement(); 
            try{
                String query = "select * from Course";

                PreparedStatement preparedStmt = this.timtableCon.prepareStatement(query);

                ResultSet rs = preparedStmt.executeQuery();
                
                while(rs.next()){
                    Course tempCourse = new Course();
                    tempCourse.setCourseDetailsUsingDatabase(rs.getString(1),rs.getString(2)); //check
                    this.courses.add(tempCourse);
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }  
        }
        catch(Exception e)
        {
            System.out.println(e);
        }        
    }
    
    
    public void setDepartmentsUsingDatabase(){
        try{         
            String connection = "jdbc:sqlserver://localhost:1433;databaseName=project"; //check
            this.timtableCon= (Connection) DriverManager.getConnection(connection,"ramin_rafi","12569");
            this.timetablestmt = (Statement) this.timtableCon.createStatement(); 
            try{
                String query = "select * from Department";

                PreparedStatement preparedStmt = this.timtableCon.prepareStatement(query);

                ResultSet rs = preparedStmt.executeQuery();
                
                while(rs.next()){
                    Department tempDep = new Department();
                    tempDep.setDepartmentDetailsUsingDatabase(rs.getString(1)); //!
                    this.departments.add(tempDep);
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            } 
        }
        catch(Exception e)
        {
            System.out.println(e);
        }        
    }
    
    public void setUsersUsingDatabase(){
        try{         
            String connection = "jdbc:sqlserver://localhost:1433;databaseName=project"; //check
            this.timtableCon= (Connection) DriverManager.getConnection(connection,"ramin_rafi","12569");
            this.timetablestmt = (Statement) this.timtableCon.createStatement(); 
            try{
                String query = "select * from User";

                PreparedStatement preparedStmt = this.timtableCon.prepareStatement(query);

                ResultSet rs = preparedStmt.executeQuery();
               
                while(rs.next()){
                    User tempUser= new User();
                   // this.
                   // this.users.add(tempUser);
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }      
        }
        catch(Exception e)
        {
            System.out.println(e);
        }        
    }
    
    public void setInstructorsUsingDatabase(){
        try{         
            String connection = "jdbc:sqlserver://localhost:1433;databaseName=project"; //check
            this.timtableCon= (Connection) DriverManager.getConnection(connection,"ramin_rafi","12569");
            this.timetablestmt = (Statement) this.timtableCon.createStatement(); 
            try{
                String query = "select * from Instructor";

                PreparedStatement preparedStmt = this.timtableCon.prepareStatement(query);

                ResultSet rs = preparedStmt.executeQuery();
               
                while(rs.next()){
                    Instructor tempInstructor= new Instructor();
                    tempInstructor.setInstructorDetailsUsingDatabase(rs.getString(1)); //!
                    this.instructors.add(tempInstructor);
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }      
        }
        catch(Exception e)
        {
            System.out.println(e);
        }        
    }
    
     public void setStudentsUsingDatabase(){
        try{         
            String connection = "jdbc:sqlserver://localhost:1433;databaseName=project"; //check
            this.timtableCon= (Connection) DriverManager.getConnection(connection,"ramin_rafi","12569");
            this.timetablestmt = (Statement) this.timtableCon.createStatement(); 
            try{
                String query = "select * from Student";

                PreparedStatement preparedStmt = this.timtableCon.prepareStatement(query);

                ResultSet rs = preparedStmt.executeQuery();
                
                while(rs.next()){
                     Student tempStudent= new Student();
                    //tempStudent.setStudentDetailsUsingDatabase(rs.getString(1)); //!
                    //this.students.add(tempStudent);
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            } 
        }
        catch(Exception e)
        {
            System.out.println(e);
        }        
    }
     
    public void setBatchesUsingDatabase(){
        try{         
            String connection = "jdbc:sqlserver://localhost:1433;databaseName=project"; //check
            this.timtableCon= (Connection) DriverManager.getConnection(connection,"ramin_rafi","12569");
            this.timetablestmt = (Statement) this.timtableCon.createStatement(); 
            try{
                String query = "select * from Batch";

                PreparedStatement preparedStmt = this.timtableCon.prepareStatement(query);

                ResultSet rs = preparedStmt.executeQuery();

                while(rs.next()){
                    Batch tempBatch= new Batch();
                    
                    tempBatch.setBatchDetailsUsingDatabase(rs.getString(1)); //!
                    
                    this.batches.add(tempBatch);
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            } 
        }
        catch(Exception e)
        {
            System.out.println(e);
        }        
    }
    
    public void setSectionsUsingDatabase(){
        try{         
            String connection = "jdbc:sqlserver://localhost:1433;databaseName=project"; //check
            this.timtableCon= (Connection) DriverManager.getConnection(connection,"ramin_rafi","12569");
            this.timetablestmt = (Statement) this.timtableCon.createStatement(); 
        
            try{
                String query = "select * from Section";

                PreparedStatement preparedStmt = this.timtableCon.prepareStatement(query);

                ResultSet rs = preparedStmt.executeQuery();
                
                while(rs.next()){
                    Section tempSection = new Section();
                    tempSection.setSectionDetailsUsingDatabase(rs.getString(1),rs.getString(4)); //!
                    this.sections.add(tempSection);
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }        
    }
    
    public void displayAllTimeSlotsUsingDatabase(){
        try{         
            String connection = "jdbc:sqlserver://localhost:1433;databaseName=project"; //check
            this.timtableCon= (Connection) DriverManager.getConnection(connection,"ramin_rafi","12569");
            this.timetablestmt = (Statement) this.timtableCon.createStatement(); 
            try{
                String s = "select * from TimeSlot";
                ResultSet rs = timetablestmt.executeQuery(s);
                while (rs.next()){
                    System.out.println("Day: "+rs.getString(4)+"\nVenueID: "+rs.getInt(6)+"\nStart Time:  "+rs.getString(2)+"\nEnd Time:  "+rs.getString(3)+"\nDuration: "+rs.getFloat(5));      
                    System.out.println("\n----------------------------------------------------\n");
                }
            }
            catch(Exception e){
            System.out.println(e);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
     public void displayAllTimeSlotsUsingDatabaseONGUI(javax.swing.JTextArea jTextArea1){
        try{         
            String connection = "jdbc:sqlserver://localhost:1433;databaseName=project"; //check
            this.timtableCon= (Connection) DriverManager.getConnection(connection,"ramin_rafi","12569");
            this.timetablestmt = (Statement) this.timtableCon.createStatement(); 
            try{
                String s = "select * from TimeSlot";
               
                ResultSet rs = timetablestmt.executeQuery(s);
                while (rs.next()){
                    
                    jTextArea1.append("Course = "+rs.getString(8)+" Section = "+rs.getString(9)+"\n");
                    jTextArea1.append("Day: "+rs.getString(4)+"\nVenueID: "+rs.getInt(6)+"\nStart Time:  "+rs.getString(2)+"\nEnd Time:  "+rs.getString(3)+"\nDuration: "+rs.getFloat(5));      
                    jTextArea1.append("\n----------------------------------------------------\n");
                    
                }
            }
            catch(Exception e){
            System.out.println(e);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public void displayTimetableInstructorwise(String insID)
    {
         try{ 
             ArrayList <String> course = new ArrayList <String>() ;
             ArrayList <String> sec = new ArrayList <String>() ;
             String connection = "jdbc:sqlserver://localhost:1433;databaseName=project"; //check
             this.timtableCon= (Connection) DriverManager.getConnection(connection,"ramin_rafi","12569");
             this.timetablestmt = (Statement) this.timtableCon.createStatement(); 
             try{
                String s = "select * from Instructor where instructorID=?";
                PreparedStatement preparedStmt = timtableCon.prepareStatement(s);
                preparedStmt.setString(1,insID);
                
                // execute the preparedstatement
                ResultSet rs = preparedStmt.executeQuery(); 
                if(!rs.next())
                {
                    System.out.println("Please enter valid instructor");
                    
                }
                    
               

                while (rs.next()){
                      course.add(rs.getString(4));
                      sec.add(rs.getString(5));
                }
                        
                    
                
                int i=0;
                while((i<course.size()) & (i<sec.size()))
                {
                    String s1 = "select * from TimeSlot where courseID=? and Section=?";
                    PreparedStatement preparedStmt1 = timtableCon.prepareStatement(s1);
                    preparedStmt1.setString(1,course.get(i));
                    preparedStmt1.setString(2,sec.get(i));

                    // execute the preparedstatement
                    ResultSet rs1 = preparedStmt1.executeQuery(); 
                      

                    while (rs1.next()){
                        System.out.println("Day: "+rs1.getString(4)+"\nVenueID: "+rs1.getInt(6)+"\nStart Time:  "+rs1.getString(2)+"\nEnd Time:  "+rs1.getString(3)+"\nDuration: "+rs1.getFloat(5));      
                        System.out.println("\n----------------------------------------------------\n");
                    }
                    i++;
                }
                
            }
            catch(Exception e){
            System.out.println(e);
           
            }
        }
        catch(Exception e){
            System.out.println(e);
            
        }
    }
     public void displayTimetableInstructorwiseONGUI(String insID,javax.swing.JTextArea jTextArea1)
    {
         try{ 
             ArrayList <String> course = new ArrayList <String>() ;
             ArrayList <String> sec = new ArrayList <String>() ;
             String connection = "jdbc:sqlserver://localhost:1433;databaseName=project"; //check
             this.timtableCon= (Connection) DriverManager.getConnection(connection,"ramin_rafi","12569");
             this.timetablestmt = (Statement) this.timtableCon.createStatement(); 
             try{
                String s = "select * from Instructor where instructorID=?";
                PreparedStatement preparedStmt = timtableCon.prepareStatement(s);
                preparedStmt.setString(1,insID);
                
                // execute the preparedstatement
                ResultSet rs = preparedStmt.executeQuery();
                
                    while (rs.next()){
                        course.add(rs.getString(4));
                        sec.add(rs.getString(5));
                    }
                    int i=0;
                    while((i<course.size()) & (i<sec.size()))
                    {
                        String s1 = "select * from TimeSlot where courseID=? and Section=?";
                        PreparedStatement preparedStmt1 = timtableCon.prepareStatement(s1);
                        preparedStmt1.setString(1,course.get(i));
                        preparedStmt1.setString(2,sec.get(i));

                        // execute the preparedstatement
                        ResultSet rs1 = preparedStmt1.executeQuery(); 
                        jTextArea1.append("CourseID = "+course.get(i)+" Section = "+sec.get(i)+"\n");
                        while (rs1.next()){
                            jTextArea1.append("Day: "+rs1.getString(4)+"\nVenueID: "+rs1.getInt(6)+"\nStart Time:  "+rs1.getString(2)+"\nEnd Time:  "+rs1.getString(3)+"\nDuration: "+rs1.getFloat(5));      
                            jTextArea1.append("\n----------------------------------------------------\n");
                        }
                        i++;
                    }
            }
            catch(Exception e){
            System.out.println(e);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void displayTimetableCoursewise(String cID)
    {
         try{ 
             ArrayList <String> sec = new ArrayList <String>() ;
             String connection = "jdbc:sqlserver://localhost:1433;databaseName=project"; //check
             this.timtableCon= (Connection) DriverManager.getConnection(connection,"ramin_rafi","12569");
             this.timetablestmt = (Statement) this.timtableCon.createStatement(); 
             try{
                String s = "select * from Section where courseID=?";
                PreparedStatement preparedStmt = timtableCon.prepareStatement(s);
                preparedStmt.setString(1,cID);
                
                // execute the preparedstatement
                ResultSet rs = preparedStmt.executeQuery(); 
               
                while (rs.next()){
                    sec.add(rs.getString(1));
                }
                int i=0;
                while( i<sec.size())
                {
                    String s1 = "select * from TimeSlot where courseID=? and Section=?";
                    PreparedStatement preparedStmt1 = timtableCon.prepareStatement(s1);
                    preparedStmt1.setString(1,cID);
                    preparedStmt1.setString(2,sec.get(i));

                    // execute the preparedstatement
                    ResultSet rs1 = preparedStmt1.executeQuery(); 

                    while (rs1.next()){
                        System.out.println("Day: "+rs1.getString(4)+"\nVenueID: "+rs1.getInt(6)+"\nStart Time:  "+rs1.getString(2)+"\nEnd Time:  "+rs1.getString(3)+"\nDuration: "+rs1.getFloat(5));      
                        System.out.println("\n----------------------------------------------------\n");
                    }
                    i++;
                }
                
            }
            catch(Exception e){
            System.out.println(e);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
     public void displayTimetableCoursewiseONGUI(String cID,javax.swing.JTextArea jTextArea1 )
    {
         try{ 
             ArrayList <String> sec = new ArrayList <String>() ;
             String connection = "jdbc:sqlserver://localhost:1433;databaseName=project"; //check
             this.timtableCon= (Connection) DriverManager.getConnection(connection,"ramin_rafi","12569");
             this.timetablestmt = (Statement) this.timtableCon.createStatement(); 
             try{
                String s = "select * from Section where courseID=?";
                PreparedStatement preparedStmt = timtableCon.prepareStatement(s);
                preparedStmt.setString(1,cID);
                
                // execute the preparedstatement
                ResultSet rs = preparedStmt.executeQuery(); 
               
                while (rs.next()){
                    sec.add(rs.getString(1));
                }
                int i=0;
                while( i<sec.size())
                {
                    String s1 = "select * from TimeSlot where courseID=? and Section=?";
                    PreparedStatement preparedStmt1 = timtableCon.prepareStatement(s1);
                    preparedStmt1.setString(1,cID);
                    preparedStmt1.setString(2,sec.get(i));

                    // execute the preparedstatement
                    ResultSet rs1 = preparedStmt1.executeQuery(); 
                    jTextArea1.append("CourseID = "+cID+" Section = "+sec.get(i)+"\n");

                    while (rs1.next()){
                        jTextArea1.append("Day: "+rs1.getString(4)+"\nVenueID: "+rs1.getInt(6)+"\nStart Time:  "+rs1.getString(2)+"\nEnd Time:  "+rs1.getString(3)+"\nDuration: "+rs1.getFloat(5));      
                        jTextArea1.append("\n----------------------------------------------------\n");
                    }
                    i++;
                
                }
             }
             catch(Exception e)
             {
                 System.out.println(e);
             }
            }
             
             catch(Exception e)
             {
                  System.out.println(e);
            }
    }
        
    
     public void displayTimetableONGUI(javax.swing.JTextArea jTextArea1)
    {
        this.displayAllTimeSlotsUsingDatabaseONGUI(jTextArea1);
    }
    public void displayTimetableBatchDept(String bID, String dID)
    {
         try{ 
             ArrayList <String> course = new ArrayList <String>() ;
             String connection = "jdbc:sqlserver://localhost:1433;databaseName=project"; //check
             this.timtableCon= (Connection) DriverManager.getConnection(connection,"ramin_rafi","12569");
             this.timetablestmt = (Statement) this.timtableCon.createStatement(); 
             try{
                String query = "Select * from Course where batchID = ? and departmentID = ?";
                PreparedStatement preparedStmt = timtableCon.prepareStatement(query);
                preparedStmt.setString(1,bID);
                preparedStmt.setString(2,dID);
                ResultSet rs = preparedStmt.executeQuery();
               
                 while(rs.next())
                    {
                        course.add(rs.getString(1));
                    }
                    int i=0;

                    while(i<course.size())
                    {
                        String query2 = "Select * from TimeSlot where courseID = ?";
                        PreparedStatement preparedStmt2 = timtableCon.prepareStatement(query2);
                        preparedStmt2.setString(1,course.get(i));
                        ResultSet rs2 = preparedStmt2.executeQuery();
                        while(rs2.next())
                        {
                            System.out.println("Day: "+rs2.getString(4)+"\nVenueID: "+rs2.getInt(6)+"\nStart Time:  "+rs2.getString(2)+"\nEnd Time:  "+rs2.getString(3)+"\nDuration: "+rs2.getFloat(5));      
                            System.out.println("\n----------------------------------------------------\n");
                        }
                        i++;

                    }
                
            }
            catch(Exception e){
            System.out.println(e);
           
            }
        }
        catch(Exception e){
            System.out.println(e);
            
        }
    }
      public boolean displayTimetableBatchDeptONGUI(String bID, String dID,javax.swing.JTextArea jTextArea1)
    {
         try{ 
             ArrayList <String> course = new ArrayList <String>() ;
             String connection = "jdbc:sqlserver://localhost:1433;databaseName=project"; //check
             this.timtableCon= (Connection) DriverManager.getConnection(connection,"ramin_rafi","12569");
             this.timetablestmt = (Statement) this.timtableCon.createStatement(); 
             try{
                String query = "Select * from Course where batchID = ? and departmentID = ?";
                PreparedStatement preparedStmt = timtableCon.prepareStatement(query);
                preparedStmt.setString(1,bID);
                preparedStmt.setString(2,dID);
                ResultSet rs = preparedStmt.executeQuery();
                if(!rs.next())
                {
                    jTextArea1.append("Please enter valid batchID and departmentID");
                    return false;
                }
                else
                {
                    while(rs.next())
                    {
                        course.add(rs.getString(1));
                    }
                    int i=0;

                    while(i<course.size())
                    {
                        String query2 = "Select * from TimeSlot where courseID = ?";
                        PreparedStatement preparedStmt2 = timtableCon.prepareStatement(query2);
                        preparedStmt2.setString(1,course.get(i));
                        ResultSet rs2 = preparedStmt2.executeQuery();
                        jTextArea1.append("Course = "+course.get(i)+"\n");
                        while(rs2.next())
                        {
                            jTextArea1.append("Day: "+rs2.getString(4)+"\nVenueID: "+rs2.getInt(6)+"\nStart Time:  "+rs2.getString(2)+"\nEnd Time:  "+rs2.getString(3)+"\nDuration: "+rs2.getFloat(5));      
                            jTextArea1.append("\n----------------------------------------------------\n");
                        }
                        i++;

                    }
                    return true;
                }
            }
            catch(Exception e){
            System.out.println(e);
            return false;
            }
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    public void displayAllCourses(){
        for (int i=0;i<courses.size();i++)
        {
            courses.get(i).printCourse();
        }
    }
    
    public void displayAllBatches(){
        for (int i=0;i<batches.size();i++)
        {
            batches.get(i).printBatch();
        }
    }
    
    public String getTitle(){
        return title;
    }  
    ArrayList<TimeSlot> getSlots()
    {
        return slots;
    }
    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Batch> getBatches() {
        return batches;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }
  
    public ArrayList <Instructor> getInstructors() {
        return instructors;
    }  
}

