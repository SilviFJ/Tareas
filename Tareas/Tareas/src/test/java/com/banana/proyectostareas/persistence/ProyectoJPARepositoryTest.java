package com.banana.proyectostareas.persistence;

import com.banana.proyectostareas.proyectostareas.model.Proyecto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProyectoJPARepositoryTest {

    @Autowired
    private ProyectoJPARepository proyectoRepository;

    @Test
    public void testGuardarYRecuperarProyecto() {
        // Crear un proyecto
        Proyecto proyecto = new Proyecto();
        proyecto.setNombre("Proyecto de prueba");
        proyecto.setFechaCreacion(LocalDate.now());

        // Guardar el proyecto en la base de datos
        Proyecto proyectoGuardado = proyectoRepository.save(proyecto);

        // Recuperar todos los proyectos de la base de datos
        List<Proyecto> proyectosRecuperados = proyectoRepository.findAll();

        // Verificar que el proyecto se haya guardado correctamente
        assertNotNull(proyectoGuardado, "El proyecto guardado no debería ser nulo");
        assertEquals(1, proyectosRecuperados.size(), "Debe haber un proyecto en la base de datos");
        Proyecto proyectoRecuperado = proyectosRecuperados.get(0);
        assertEquals("Proyecto de prueba", proyectoRecuperado.getNombre(), "El nombre del proyecto no coincide");
        assertEquals(LocalDate.now(), proyectoRecuperado.getFechaCreacion(), "La fecha de creación del proyecto no coincide");
    }
}
