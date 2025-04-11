-- ======================================
-- ========== CREACIÓN DE TABLAS ==========
-- ======================================

-- Tabla de Rutinas
CREATE TABLE rutinas (
    idRutinas INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR(100) NOT NULL UNIQUE
);

-- Tabla de Ejercicios
CREATE TABLE ejercicios (
    idEjercicios INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(100),
    imagen VARCHAR(100),
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
    ('Curl con barra', 'Ejercicio principal para bíceps, realizar con peso moderado a pesado, lento y controlado', 'curl_barra.jpg', 4, 10, 60),
    ('Curl martillo', 'Ejercicio para trabajar bíceps y braquial, mantener los codos fijos', 'curl_martillo.jpg', 3, 12, 60),
    ('Curl concentrado', 'Ejercicio de aislamiento, forma estricta y controlada, no balancear el cuerpo', 'curl_concentrado.jpg', 3, 12, 60),

-- TRÍCEPS
    ('Fondos en paralelas', 'Ejercicio compuesto, puede hacerse con peso, explosivo pero controlado en bajada', 'fondos_paralelas.jpg', 4, 10, 60),
    ('Extensión con cuerda en polea', 'Mantener codos fijos, movimiento controlado, enfoque en la contracción del tríceps', 'extension_cuerda.jpg', 3, 12, 45),
    ('Press francés con barra Z', 'Movimiento lento, sin balancear los codos, evitar mover los hombros', 'press_frances.jpg', 3, 10, 60),

-- PECHO
    ('Press de banca', 'Ejercicio base para pecho, realizar con peso moderado a pesado, forma controlada', 'press_banca.jpg', 4, 8, 90),
    ('Press inclinado con mancuernas', 'Enfocado en parte superior del pecho, movimiento controlado', 'press_inclinado.jpg', 3, 10, 60),
    ('Aperturas con mancuernas', 'Movimiento de estiramiento, usar peso moderado, lento y amplio', 'aperturas_mancuernas.jpg', 3, 12, 45),

-- ESPALDA
    ('Dominadas', 'Ejercicio compuesto, enfoque en amplitud, explosivo al subir, lento al bajar', 'dominadas.jpg', 4, 8, 90),
    ('Remo con barra', 'Remo pesado para grosor de espalda, mantener espalda recta y controlar el movimiento', 'remo_barra.jpg', 4, 10, 60),
    ('Jalón al pecho', 'Enfocado en dorsales, controlar el recorrido y mantener el pecho elevado', 'jalon_pecho.jpg', 3, 12, 60),

-- HOMBROS
    ('Press militar con barra', 'Ejercicio principal de hombros, controlado, evitar balanceos', 'press_militar.jpg', 4, 8, 90),
    ('Elevaciones laterales', 'Para deltoides laterales, peso moderado, lento y sin impulso', 'elevaciones_laterales.jpg', 3, 15, 45),
    ('Pájaros con mancuernas', 'Para deltoides posteriores, lento y controlado', 'pajaros_mancuernas.jpg', 3, 15, 45),

-- TRAPECIOS
    ('Encogimientos con mancuernas', 'Movimiento corto, controlar la subida y bajada, sin girar los hombros', 'encogimientos.jpg', 4, 15, 30),
    ('Remo al cuello con barra Z', 'Activación de trapecios y deltoides, evitar peso excesivo', 'remo_cuello.jpg', 3, 12, 45),
    ('Farmer’s walk', 'Caminar sosteniendo peso, mantener postura erguida, buen ejercicio funcional', 'farmers_walk.jpg', 3, 30, 60),

-- FEMORAL
    ('Peso muerto rumano', 'Lento y controlado, enfocado en estiramiento del femoral', 'peso_muerto_rumano.jpg', 4, 10, 60),
    ('Curl femoral en máquina', 'Enfocado y controlado, sin rebotes', 'curl_femoral.jpg', 3, 12, 45),
    ('Buenos días', 'Ejercicio técnico, mantener la espalda recta y movimiento lento', 'buenos_dias.jpg', 3, 10, 60),

-- GLÚTEOS
    ('Hip thrust', 'Ejercicio principal para glúteos, concentrarse en la contracción al subir', 'hip_thrust.jpg', 4, 12, 60),
    ('Patada de glúteo en polea', 'Movimiento de aislamiento, forma estricta y controlada', 'patada_polea.jpg', 3, 15, 45),
    ('Peso muerto sumo', 'Apertura de piernas amplia, foco en glúteos e internos de piernas', 'peso_muerto_sumo.jpg', 4, 10, 60),

-- CUÁDRICEPS
    ('Sentadillas', 'Ejercicio básico y completo, debe realizarse con buena técnica y control', 'sentadillas.jpg', 4, 10, 90),
    ('Prensa de piernas', 'Evitar bloquear las rodillas, movimiento completo y controlado', 'prensa_piernas.jpg', 4, 12, 60),
    ('Extensión de piernas', 'Ejercicio de aislamiento, lento y con buena contracción', 'extension_piernas.jpg', 3, 15, 45);

-- Asociación de Rutinas y Ejercicios

-- Rutina LUNES (Pecho + Bíceps + Trapecio)
INSERT INTO rutinas_ejercicios (rutina_id, ejercicio_id) VALUES
    (1, 7), (1, 8), (1, 1), (1, 2), (1, 19), (1, 20);

-- Rutina MARTES (Espalda + Tríceps + Abdomen)
INSERT INTO rutinas_ejercicios (rutina_id, ejercicio_id) VALUES
    (2, 10), (2, 11), (2, 4), (2, 5), (2, 12), (2, 6);

-- Rutina MIÉRCOLES (Piernas: Cuádriceps + Femorales + Glúteos)
INSERT INTO rutinas_ejercicios (rutina_id, ejercicio_id) VALUES
    (3, 28), (3, 29), (3, 30), (3, 16), (3, 17), (3, 22);

-- Rutina JUEVES (Hombros + Pecho + Bíceps)
INSERT INTO rutinas_ejercicios (rutina_id, ejercicio_id) VALUES
    (4, 13), (4, 14), (4, 15), (4, 9), (4, 3), (4, 1);

-- Rutina VIERNES (Espalda + Piernas + Glúteos/Core)
INSERT INTO rutinas_ejercicios (rutina_id, ejercicio_id) VALUES
    (5, 10), (5, 18), (5, 23), (5, 24), (5, 27), (5, 17);

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
