package com.banana.proyectostareas.persistence;

import com.banana.proyectostareas.model.Tarea;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TareaJPARepositoryTest {

    @Autowired
    private TareaJPARepository tareaRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testGuardarYRecuperarTarea() {
        // Crear una tarea
        Tarea tarea = new Tarea();
        tarea.setDescripcion("Tarea de prueba");
        tarea.setFechaLimite(LocalDate.now());
        tarea.setOrden(1);
        tarea.setCompletada(false);

        // Guardar la tarea en la base de datos
        Tarea tareaGuardada = tareaRepository.save(tarea);

        // Recuperar todas las tareas de un proyecto de la base de datos por su ID
        //List<Tarea> tareasRecuperadas = tareaRepository.findByProject(tareaGuardada.getIdProyecto());

        // Verificar que la tarea se haya guardado correctamente
        assertNotNull(tareaGuardada.getId(), "La tarea guardada no debería ser nula");

        // Recuperar la tarea de la base de datos por su ID, utilizando el EntityManager
        Tarea tareaRecuperada = entityManager.find(Tarea.class, tareaGuardada.getId());

        //Verificar que la tarea se haya guardado correctamente
        assertNotNull(tareaRecuperada, "La tarea deberia estar en la baase de datos");
    }
}
