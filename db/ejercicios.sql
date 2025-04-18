-- ======================================
-- ========== CREACIÓN DE TABLAS ==========
-- ======================================

-- Tabla de Rutinas
CREATE TABLE rutinas (
    idRutinas INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL UNIQUE
);

-- Tabla de Ejercicios
CREATE TABLE ejercicios (
    idEjercicios INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    descripcion TEXT,
    imagen INTEGER,
    series INTEGER CHECK(series > 0),
    repeticiones INTEGER CHECK(repeticiones > 0),
    tiempoDescanso INTEGER CHECK(tiempoDescanso >= 10)
);

-- Tabla de relación Rutinas-Ejercicios (muchos a muchos)
CREATE TABLE rutinas_ejercicios (
    rutina_id INTEGER NOT NULL,
    ejercicio_id INTEGER NOT NULL,
    PRIMARY KEY (rutina_id, ejercicio_id),
    FOREIGN KEY (rutina_id) REFERENCES rutinas(idRutinas)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (ejercicio_id) REFERENCES ejercicios(idEjercicios)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


-- ======================================
-- ========== INSERCIÓN DE DATOS ==========
-- ======================================

-- Rutinas
INSERT INTO rutinas (nombre) VALUES
    ('Rutina LUNES'),
    ('Rutina MARTES'),
    ('Rutina MIERCOLES'),
    ('Rutina JUEVES'),
    ('Rutina VIERNES');

-- Ejercicios
INSERT INTO ejercicios (nombre, descripcion, imagen, series, repeticiones, tiempoDescanso) VALUES
-- BÍCEPS
    ('Curl con barra', 'Ejercicio principal para bíceps, realizar con peso moderado a pesado, lento y controlado', 2130968577, 4, 10, 60),
    ('Curl martillo', 'Ejercicio para trabajar bíceps y braquial, mantener los codos fijos', 2130968605, 3, 12, 60),
    ('Curl concentrado', 'Ejercicio de aislamiento, forma estricta y controlada, no balancear el cuerpo', 2130968603, 3, 12, 60),

-- TRÍCEPS
    ('Fondos en paralelas', 'Ejercicio compuesto, puede hacerse con peso, explosivo pero controlado en bajada', 2130968578, 4, 10, 60),
    ('Extensión con cuerda en polea', 'Mantener codos fijos, movimiento controlado, enfoque en la contracción del tríceps', 2130968609, 3, 12, 45),
    ('Press francés con barra Z', 'Movimiento lento, sin balancear los codos, evitar mover los hombros', 2130968619, 3, 10, 60),

-- PECHO
    ('Press de banca', 'Ejercicio base para pecho, realizar con peso moderado a pesado, forma controlada', 2130968600, 4, 8, 90),
    ('Press inclinado con mancuernas', 'Enfocado en parte superior del pecho, movimiento controlado', 2130968620, 3, 10, 60),
    ('Aperturas con mancuernas', 'Movimiento de estiramiento, usar peso moderado, lento y amplio', 2130968601, 3, 12, 45),

-- ESPALDA
    ('Dominadas', 'Ejercicio compuesto, enfoque en amplitud, explosivo al subir, lento al bajar', 2130968606, 4, 8, 90),
    ('Remo con barra', 'Remo pesado para grosor de espalda, mantener espalda recta y controlar el movimiento', 2130968623, 4, 10, 60),
    ('Jalón al pecho', 'Enfocado en dorsales, controlar el recorrido y mantener el pecho elevado', 2130968613, 3, 12, 60),

-- HOMBROS
    ('Press militar con barra', 'Ejercicio principal de hombros, controlado, evitar balanceos', 2130968621, 4, 8, 90),
    ('Elevaciones laterales', 'Para deltoides laterales, peso moderado, lento y sin impulso', 2130968607, 3, 15, 45),
    ('Pájaros con mancuernas', 'Para deltoides posteriores, lento y controlado', 2130968614, 3, 15, 45),

-- TRAPECIOS
    ('Encogimientos con mancuernas', 'Movimiento corto, controlar la subida y bajada, sin girar los hombros', 2130968608, 4, 15, 30),
    ('Remo al cuello con barra Z', 'Activación de trapecios y deltoides, evitar peso excesivo', 2130968622, 3, 12, 45),
    ('Farmer’s walk', 'Caminar sosteniendo peso, mantener postura erguida, buen ejercicio funcional', 2130968611, 3, 30, 60),

-- FEMORAL
    ('Peso muerto rumano', 'Lento y controlado, enfocado en estiramiento del femoral', 2130968617, 4, 10, 60),
    ('Curl femoral en máquina', 'Enfocado y controlado, sin rebotes', 2130968604, 3, 12, 45),
    ('Buenos días', 'Ejercicio técnico, mantener la espalda recta y movimiento lento', 2130968602, 3, 10, 60),

-- GLÚTEOS
    ('Hip thrust', 'Ejercicio principal para glúteos, concentrarse en la contracción al subir', 2130968612, 4, 12, 60),
    ('Patada de glúteo en polea', 'Movimiento de aislamiento, forma estricta y controlada', 2130968615, 3, 15, 45),
    ('Peso muerto sumo', 'Apertura de piernas amplia, foco en glúteos e internos de piernas', 2130968616, 4, 10, 60),

-- CUÁDRICEPS
    ('Sentadillas', 'Ejercicio básico y completo, debe realizarse con buena técnica y control', 2130968624, 4, 10, 90),
    ('Prensa de piernas', 'Evitar bloquear las rodillas, movimiento completo y controlado', 2130968618, 4, 12, 60),
    ('Extensión de piernas', 'Ejercicio de aislamiento, lento y con buena contracción', 2130968610, 3, 15, 45);

-- Asociación de Rutinas y Ejercicios

-- Rutina LUNES (Pecho + Bíceps + Trapecio)
INSERT INTO rutinas_ejercicios (rutina_id, ejercicio_id) VALUES
    (1, 7),  -- Press de banca
    (1, 8),  -- Press inclinado con mancuernas
    (1, 1),  -- Curl con barra
    (1, 2),  -- Curl martillo
    (1, 16), -- Encogimientos con mancuernas
    (1, 17); -- Remo al cuello con barra Z

-- Rutina MARTES (Espalda + Tríceps + Abdomen)
INSERT INTO rutinas_ejercicios (rutina_id, ejercicio_id) VALUES
    (2, 10), -- Dominadas
    (2, 11), -- Remo con barra
    (2, 4),  -- Fondos en paralelas
    (2, 5),  -- Extensión con cuerda en polea
    (2, 6),  -- Press francés con barra Z
    (2, 18); -- Farmer’s walk

-- Rutina MIÉRCOLES (Piernas: Cuádriceps + Femorales + Glúteos)
INSERT INTO rutinas_ejercicios (rutina_id, ejercicio_id) VALUES
    (3, 25), -- Sentadillas
    (3, 26), -- Prensa de piernas
    (3, 27), -- Extensión de piernas
    (3, 19), -- Peso muerto rumano
    (3, 20), -- Curl femoral en máquina
    (3, 22); -- Hip thrust

-- Rutina JUEVES (Hombros + Pecho + Bíceps)
INSERT INTO rutinas_ejercicios (rutina_id, ejercicio_id) VALUES
    (4, 13), -- Press militar con barra
    (4, 14), -- Elevaciones laterales
    (4, 15), -- Pájaros con mancuernas
    (4, 9),  -- Aperturas con mancuernas
    (4, 3),  -- Curl concentrado
    (4, 1);  -- Curl con barra

-- Rutina VIERNES (Espalda + Piernas + Glúteos/Core)
INSERT INTO rutinas_ejercicios (rutina_id, ejercicio_id) VALUES
    (5, 12), -- Jalón al pecho
    (5, 24), -- Peso muerto sumo
    (5, 23), -- Patada de glúteo en polea
    (5, 21), -- Buenos días
    (5, 17), -- Remo al cuello con barra Z
    (5, 18); -- Farmer’s walk


-- ======================================
-- ========== CONSULTAS SELECT ==========
-- ======================================

-- Todas las rutinas
SELECT * FROM rutinas;

-- Todos los ejercicios
SELECT * FROM ejercicios;

-- Todas las relaciones entre rutinas y ejercicios
SELECT * FROM rutinas_ejercicios;

-- Ejercicios de una rutina específica por nombre
SELECT 
    r.nombre AS rutina,
    e.nombre AS ejercicio
FROM rutinas_ejercicios re
JOIN rutinas r ON re.rutina_id = r.idRutinas
JOIN ejercicios e ON re.ejercicio_id = e.idEjercicios
WHERE r.nombre = 'Rutina LUNES';

-- Ejercicios de una rutina específica por ID
SELECT 
    r.idRutinas AS rutina_id,
    r.nombre AS rutina,
    e.idEjercicios AS ejercicio_id,
    e.nombre AS ejercicio
FROM rutinas_ejercicios re
JOIN rutinas r ON re.rutina_id = r.idRutinas
JOIN ejercicios e ON re.ejercicio_id = e.idEjercicios
WHERE r.idRutinas = 1;

-- ======================================
-- ========== ACTUALIZACIONES ==========
-- ======================================

-- Actualizar rutina
UPDATE rutinas
SET nombre = 'Rutina LUNES ACTUALIZADA'
WHERE idRutinas = 1;

-- Actualizar ejercicio
UPDATE ejercicios
SET descripcion = 'Nueva descripción del ejercicio', series = 5
WHERE idEjercicios = 1;

-- Cambiar ejercicio dentro de una rutina
UPDATE rutinas_ejercicios
SET ejercicio_id = 2
WHERE rutina_id = 1 AND ejercicio_id = 1;

-- ======================================
-- ========== ELIMINACIONES ==========
-- ======================================

-- Eliminar una rutina
DELETE FROM rutinas
WHERE idRutinas = 5;

-- Eliminar un ejercicio
DELETE FROM ejercicios
WHERE idEjercicios = 30;

-- Remover ejercicio de una rutina
DELETE FROM rutinas_ejercicios
WHERE rutina_id = 1 AND ejercicio_id = 2;
