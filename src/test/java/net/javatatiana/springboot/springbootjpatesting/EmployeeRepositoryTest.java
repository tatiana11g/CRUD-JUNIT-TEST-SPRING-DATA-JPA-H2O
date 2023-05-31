package net.javatatiana.springboot.springbootjpatesting;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;


import org.assertj.core.api.Assertions;

@DataJpaTest //ayuda a probar la capa de persistencia JPA y el repositorio
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryTest {
	
    @Autowired
	private EmployeeRepository employeeRepository; 

	
 // JUnit test for saveEmployee
    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveEmployeeTest(){

        Employee employee = Employee.builder()
                .firstName("Tatiana")
                .lastName("Gomez")
                .email("tatianatsslow@hotmail.com")
                .build();

        employeeRepository.save(employee);

        Assertions.assertThat(employee.getId()).isGreaterThan(0);
    }


    
   //buscar por Id 1
    @Test
    @Order(2)
    public void getEmployeeTest(){

        Employee employee = employeeRepository.findById(1L).get(); //obtenemos el id

        Assertions.assertThat(employee.getId()).isEqualTo(1L);  // se afirma  si el valor es igual a 1

    }
    
    //buscar por todos los empleados
    @Test
    @Order(3)
    public void getListOfEmployeesTest(){

        List<Employee> employees = employeeRepository.findAll(); //odtenemos la lista

        Assertions.assertThat(employees.size()).isGreaterThan(0);//afirmamos si su tamaño es mayor a 0

    }
    
    //Actualiza email
    @Test
    @Order(4)
    @Rollback(value = false) //si se debe revertir la operacion = false
    public void updateEmployeeTest(){

        Employee employee = employeeRepository.findById(1L).get(); //se obtiene  el id

        employee.setEmail("tatianatsslow@gmail.com"); //enviamos el dato del email

        Employee employeeUpdated =  employeeRepository.save(employee); //guardamos

        Assertions.assertThat(employeeUpdated.getEmail()).isEqualTo("tatianatsslow@gmail.com");// si lo que se obtiene del getEmail es igual

    }
    
    //borrar

    @Test
    @Order(5)
    @Rollback(value = false) //deshabilitar la reversión
    public void deleteEmployeeTest(){

        Employee employee = employeeRepository.findById(1L).get();//se recupera  el objeto optional con el get

        employeeRepository.delete(employee);

        //employeeRepository.deleteById(1L);

        Employee employee1 = null;

        Optional<Employee> optionalEmployee = employeeRepository.findByEmail("tatianatsslow@gmail.com");  // llamamos al metodo creado en el repositorio

        
        if(optionalEmployee.isPresent()){
            employee1 = optionalEmployee.get(); //si la lista trae algo lo asignamos al objeto
        }

        Assertions.assertThat(employee1).isNull();
    }
    
    
    
}