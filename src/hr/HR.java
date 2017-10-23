/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Types;



/**
 *
 * @author david
 */
public class HR {

    public int insertarRegion(Region region) throws ExcepcionHR{
        String dml = "";
        int registrosAfectados = 0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");   
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "kk");

            dml = "insert into regions values(?,?)";
            PreparedStatement sentencia = conexion.prepareStatement(dml);
            sentencia.setInt(1, region.getRegionId());
            sentencia.setString(2, region.getRegionName());
            
            registrosAfectados = sentencia.executeUpdate(dml);

            sentencia.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error - Clase no Encontrada: " + ex.getMessage());
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),null,dml);
            switch (ex.getErrorCode()) {
                case 1400:  
                    excepcionHR.setMensajeErrorUsuario("El identificador del pais no puede ser nulo");
                     break;
                case 1:     
                    excepcionHR.setMensajeErrorUsuario("El identificador del pais no puede repetirse.");
                    break;
                default:    
                    excepcionHR.setMensajeErrorUsuario("Error en el sistema. Consulta con el administrador.");
                    break;
            }
            throw excepcionHR;
        }
        return registrosAfectados;
    }
    
    public int borrarRegion(int regionId){
        
        return 0;
    }
    
    public int modificarRegion(int regionId,Region region){
        
        return 0;
    }
    
    public Region leerRegion(int regionId){
        
        return null;
    }
    
    public ArrayList<Region> leerRegions(){
        
        return null;
    }
    
    
    public int insertarCountry(Country country){
        
        return 0;
    }
    
    public int borrarCountry(String countryId) throws ExcepcionHR{
        String llamada = "";
        int registrosAfectados = 0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "kk");
            llamada = "call borrarCountry(?)";
            CallableStatement sentenciaLlamable = conexion.prepareCall(llamada);
            
            sentenciaLlamable.setString(1, countryId);
            sentenciaLlamable.executeUpdate();
            
            sentenciaLlamable.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error - Clase no Encontrada: " + ex.getMessage());
        } catch (SQLException ex) {      
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),null,llamada);
            switch(ex.getErrorCode()){            
                case 2292: 
                        excepcionHR.setMensajeErrorUsuario("El pais a borrar, tiene localidades asociadas.");
                    break;
                default:    
                        excepcionHR.setMensajeErrorUsuario("Error en el sistema. Consulta con el administrador");
                    break;
            }         
            throw excepcionHR;
        }
        return 0;
    }
    
    public int modificarCountry(String countryId,Country country) throws ExcepcionHR{
        String llamada = "";
        int registrosAfectados = 0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "kk");
            llamada = "call modificarCountry(?,?,?,?)";
            CallableStatement sentenciaLlamable = conexion.prepareCall(llamada);
            
            sentenciaLlamable.setString(1, countryId);
            sentenciaLlamable.setString(1, country.getCountryId());
            sentenciaLlamable.setString(1, country.getCountryName());
            sentenciaLlamable.setInt(1, country.getRegion().getRegionId());
            sentenciaLlamable.executeUpdate();
            
            sentenciaLlamable.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error - Clase no Encontrada: " + ex.getMessage());
        } catch (SQLException ex) {      
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),null,llamada);
            switch(ex.getErrorCode()){            
                case 2292: 
                        excepcionHR.setMensajeErrorUsuario("No se puede modificar el identificador, tiene localidades asociadas.");
                    break;
                case 2291:
                        excepcionHR.setMensajeErrorUsuario("La region no existe");
                    break;
                case 1407:
                        excepcionHR.setMensajeErrorUsuario("El identificador del pais no puede ser nulo");
                    break;
                case 1:
                        excepcionHR.setMensajeErrorUsuario("El identificador de pais no puede repetirse");
                    break;
                default:    
                        excepcionHR.setMensajeErrorUsuario("Error en el sistema. Consulta con el administrador");
                    break;
            }         
            throw excepcionHR;
        }
        return 0;
    }
    
    public Country leerCountry(String countryId){
        
        return null;
    }
    
    public ArrayList<Country> leerCountries(){
        
        return null;
    }
    
    public int insertarLocation(Location location){
        
        return 0;
    }
    
    public int borrarLocation(int locationId) throws ExcepcionHR{
        String dml = "";
        int registrosAfectados = 0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "kk");
                     
            dml = "delete locations where location_id=?";    
            PreparedStatement sentencia= conexion.prepareStatement(dml);
            
            sentencia.setInt(1, locationId);          
            registrosAfectados = sentencia.executeUpdate(dml);

            sentencia.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error - Clase no Encontrada: " + ex.getMessage());
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),null,dml);
            switch (ex.getErrorCode()) {
                case 2292:  
                    excepcionHR.setMensajeErrorUsuario("La localidad a borrar, tiene departamentos asociados.");
                    break;
                default:    
                    excepcionHR.setMensajeErrorUsuario("Error en el sistema. Consulta con el administrador");
                    break;
            }
            throw excepcionHR;
        }
        return registrosAfectados;
    }
    
    public int modificarLocation(int locationId,Location location){
        
        return 0;
    }
    
    public Location leerLocation(int locationId){
        
        return null;
    }
    
    public ArrayList<Location> leerLocations(){
        
        return null;
    }
    
    public int insertarDepartment(Department department){
        
        return 0;
    }
    
    public int borrarDepartment(int departmentId){
        
        return 0;
    }
    
    public int modificarDepartment(int departmentId,Department department) throws ExcepcionHR{
        int registrosAfectados = 0;
        String dml = "";
        try {            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "kk");
            Statement sentencia = conexion.createStatement();
          
            dml = "update DEPARTMENTS SET "
                    + "DEPARTMENT_ID="+department.getDepartmentId()+","
                    + "DEPARTMENT_NAME='"+department.getDepartmentName()+"',"
                    + "MANAGER_ID="+department.getManager().getEmployeeId()+","
                    + "LOCATION_ID="+department.getLocation().getLocationId()+" "
                    + "where DEPARTMENT_ID="+departmentId;
            
            registrosAfectados = sentencia.executeUpdate(dml);
                      
            sentencia.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error - Clase no Encontrada: " + ex.getMessage());
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),null,dml);
            switch (ex.getErrorCode()) {
                case 2291:  
                    excepcionHR.setMensajeErrorUsuario("La localidad elegida no "
                            + "existe o el jefe de departamentono es un empleado de la empresa.");
                    break;
                case 1400:  
                    excepcionHR.setMensajeErrorUsuario("El identificador y el nombre "
                            + "de departamento son obligatorios");
                    break;
                case 2290:  
                    excepcionHR.setMensajeErrorUsuario("No se puede modificar el identificador "
                            + "de departamento, ya que tiene o ha tenido empleados asociados.");
                    break;
                case 1:     
                    excepcionHR.setMensajeErrorUsuario("El identificador de departamento ya existe.");
                    break;
                default:    
                    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                    break;
            }
            throw excepcionHR;
        }
        return registrosAfectados;
    }
    
    public Department leerDepartment(int departmentId){
        
        return null;
    }
    
    public ArrayList<Department> leerDepartments(){
        
        return null;
    }
    
    public int insertarEmployee(Employee employee) throws ExcepcionHR{
        String dml = null;
        int registrosAfectados = 0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "kk");
            
            dml = "insert into EMPLOYEES VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement sentencia= conexion.prepareStatement(dml);
            
            sentencia.setInt(1, employee.getEmployeeId());
            sentencia.setString(2, employee.getFirstName());
            sentencia.setString(3, employee.getLastName());
            sentencia.setString(4,employee.getEmail());
            sentencia.setString(5, employee.getPhoneNumber());
            sentencia.setDate(6, employee.getHireDate());
            sentencia.setInt(7, employee.getJob().getJobId());
            sentencia.setDouble(8, employee.getSalary());
            sentencia.setDouble(9, employee.getCommissionPct());
            sentencia.setInt(10, employee.getManager().getEmployeeId());
            sentencia.setInt(11, employee.getDepartment().getDepartmentId());
            
            registrosAfectados = sentencia.executeUpdate(dml);

            sentencia.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error - Clase no Encontrada: " + ex.getMessage());
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(),ex.getMessage(),null,dml);
            switch (ex.getErrorCode()) {
                case 2291:  
                    excepcionHR.setMensajeErrorUsuario("La localidad y el jefe no existe");
                            break;
                case 2292:  
                    excepcionHR.setMensajeErrorUsuario("No se puede modificar el identificador, tiene empleados asociados.");
                            break;
                case 1407:  
                    excepcionHR.setMensajeErrorUsuario("El nombre de departamento es obligatorio");
                            break;
                case 1:     
                    excepcionHR.setMensajeErrorUsuario("El departamento no puede repetirse");
                            break;
                default:    
                    excepcionHR.setMensajeErrorUsuario("Error en el sistema. Consulta con el administrador");
                            break;
            } throw excepcionHR;
        }
        return registrosAfectados;
    }
    
    public int borrarEmployee(int employeeId){
        
        return 0;
    }
    
    public int modificarEmployee(int employeeId,Employee employee){
        
        return 0;
    }
    
    public Employee leerEmployee(int employeeId){
        
        return null;
    }
    
    public ArrayList<Employee> leerEmployees(){
        
        return null;
    }
    
    public int insertarJob(Job job){
        
        return 0;
    }
    
    public int borrarJob(String jobId){
        
        return 0;
    }
    
    public int modificarJob(String jobId,Job job){
        
        return 0;
    }
    
    public Country leerJob(String jobId){
        
        return null;
    }
    
    public ArrayList<Job> leerJobs(){
        
        return null;
    }
    
    public int insertarJobHistory(JobHistory jobHistory){
        
        return 0;
    }
    
    public int borrarJobHistory(int employeeId, Date startDate){
        
        return 0;
    }
    
    public int modificarJobHistory(int employeeId, Date startDate,JobHistory jobHistory){
        
        return 0;
    }
    
    public JobHistory leerJobHistory(int employeeId, Date startDate){
        
        return null;
    }
    
    public ArrayList<JobHistory> leerJobHistorys(){
        
        return null;
    }

    
    
    
    
}
