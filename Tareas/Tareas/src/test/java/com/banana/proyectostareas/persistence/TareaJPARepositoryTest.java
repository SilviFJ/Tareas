package com.banana.proyectostareas.persistence;

import com.banana.proyectostareas.proyectostareas.model.Tarea;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TareaJPARepositoryTest {

    @Autowired
    private TareaJPARepository tareaRepository;

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
        List<Tarea> tareasRecuperadas = tareaRepository.findByProject(tareaGuardada.getIdProyecto());

        // Verificar que la tarea se haya guardado correctamente
        assertNotNull(tareaGuardada, "La tarea guardada no debería ser nula");
        assertEquals(1, tareasRecuperadas.size(), "Debe haber una tarea en la base de datos");
        Tarea tareaRecuperada = tareasRecuperadas.get(0);
        assertEquals("Tarea de prueba", tareaRecuperada.getDescripcion(), "La descripción de la tarea no coincide");
        assertEquals(LocalDate.now(), tareaRecuperada.getFechaLimite(), "La fecha límite de la tarea no coincide");
        assertEquals(1, tareaRecuperada.getOrden(), "El orden de la tarea no coincide");
        assertEquals(false, tareaRecuperada.isCompletada(), "El estado de la tarea no coincide");
    }
}
