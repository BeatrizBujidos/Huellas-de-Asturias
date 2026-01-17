-- ============================================
-- Script: 03_data.sql
-- Descripción: Datos iniciales para desarrollo
-- Nota: Datos de ejemplo
-- ============================================

-- Usar la base de datos
USE arte_asturiano;

-- ============================================
-- 1. Insertar Épocas históricas
-- ============================================

INSERT INTO epocas (id, nombre, descripcion, fecha_inicio, fecha_fin, caracteristicas) VALUES

(1, 'Prerrománico Asturiano. Época de Alfonso II', 'La arquitectura de este periodo representa la consolidación del arte prerrománico. Es más sobria, armónica y de clara inspiración romana y paleocristiana.', 791, 841, 'Planta basilical de tres naves, con cabecera tripartita. Arcos de medio punto peraltados sobre pilares cuadrados sin columnas. Bóvedas de cañón en la cabecera y cubiertas de madera en las naves. Contrafuertes y ventanas geminadas.'),
(2, 'Prerrománico Asturiano. Época de Ramiro I', 'La arquitectura de este periodo representa el momento de máximo esplendor, originalidad y complejidad, una especie de estilo "barroco" dentro del prerrománico.', 842, 850, 'Plantas más complejas, con cuerpos superpuestos y volúmenes escalonados, aparecen las criptas y las cámaras secretas. Uso sistemático de la bóveda de cañón, no solo en la cabecera, sino en todo el edificio. Arcos fajones y arcos perpiaños. Aparición de columnas adosadas. Contrafuertes externos y arbotantes que contrarrestan el peso de las bóvedas de piedra.'),
(3, 'Prerrománico Asturiano. Época de Alfonso III', 'Representa la última etapa del estilo prerrománico, marcado por la influencia de las nuevas corrientes estilísticas que llegaban de Europa y anuncian la transición al arte románico', 866, 910, 'Se mantiene la planta basilical de tres naves, con una cabecera donde aparecen absidiolos contrapuestos además de los tres ábsides. Se vuelve a los pilares cruciformes cuadrados, abandonando las columnas. Se introduce el arco de herradura, de clara influencia mozárabe y visigoda. Se abandona la bóveda de cañón por la cubierta de madera en las naves y los ábsides siguen cubriéndose con bóvedas de cañón o de horno.'),
(4, 'Abstracción Geométrica', 'Primera vanguardia escultórica, influenciada por el constructivismo internacional.', 1950, 1970, 'Formas puras, volúmenes geométricos, equilibrio compositivo, materiales industriales, ruptura con la tradición figurativa.'),
(5, 'Figuración Moderna', 'Reinterpretación contemporánea de la figura humana manteniendo referentes reconocibles pero con lenguaje moderno.', 1960, 1980, 'Figura humana estilizada, síntesis formal, expresionismo contenido, temas existenciales y sociales, conexión con la identidad asturiana.'),
(6, 'Escultura Pública Monumental', 'Desarrollo de obras de gran formato para espacios urbanos con carga simbólica e identitaria para la región.', 1970, 1990, 'Gran escala, integración urbana, materiales duraderos, temas históricos y sociales, función conmemorativa, diálogo con el espacio público.'),
(7, 'Conceptualismo', 'Aproximaciones conceptuales donde prima el concepto sobre la forma, con fuerte crítica social y reflexión sobre la memoria industrial.', 1990, 2010, 'Énfasis en el concepto, uso de objetos cotidianos, instalaciones site-specific, crítica institucional, memoria industrial, arte procesual.'),
(8, 'Costumbrismo Asturiano', 'Movimiento pictórico que retrata la vida rural, tradiciones y paisajes de Asturias con realismo y detalle.', 1850, 1920, 'Escenas rurales, tipos populares, paisajes asturianos, crítica social, realismo.'),
(9, 'Vanguardias en la Pintura Asturiana', 'Aparición de movimientos de vanguardia entre artistas asturianos', 1910, 2000, 'Experimentación formal, cubismo, surrealismo, expresionismo, abstracción.'),
(10, 'Informalismo Asturiano', 'Movimiento abstracto de posguerra que renovó el arte regional con énfasis en la materia y el gesto.', 1950, 1970, 'Abstracción, materia pictórica, expresionismo abstracto, arte no figurativo'),
(11, 'Figuración Crítica', 'Arte figurativo de denuncia social desarrollado durante el franquismo y la transición.', 1960, 1985, 'Figuración expresionista, crítica política, denuncia social, realismo crítico.'),
(12, 'Arte Contemporáneo', 'Producción artística actual que dialoga con tendencias globales manteniendo referentes regionales.', 1985, 2025, 'Pluralidad de lenguajes, nuevas tecnologías, conciencia ecológica, identidad revisada.');

-- ============================================
-- 2. Insertar Artistas
-- ============================================

INSERT INTO artistas (id, nombre, apellidos, fecha_nacimiento, fecha_muerte, lugar_nacimiento, biografia, estilo, imagen) VALUES

(1, 'Julia', 'Alcayde y Montoya', '1885-05-22', '1939-02-18', 'Gijón, Asturias', 'Pintora española especializada en bodegones, floreros y escenas de caza, también cultivó el paisaje y el retrato. Comenzó su formación en Madrid de la mano de Manuel Ramírez, profesor de la Escuela de Artes y Oficios. Participó en las Exposiciones Nacionales de Bellas Artes, siendo galardonada con tercera medalla en las ediciones de 1892 y 1895', 'Costumbrismo/Realismo', '/assets/imagenes/artistas/JuliaAlcayde.jpg'),
(2, 'Encarna', 'Díaz Velasco', '1954-06-18', null, 'Moreda de Aller, Asturias', 'Destacada artista visual y doctora en Historia del Arte con una trayectoria de más de 40 años. Comenzó a experimentar con la escultura a los seis años bajo la influencia de su abuelo artesano. Se formó en las Escuelas de Artes Aplicadas de Valladolid y Oviedo. Es licenciada y doctora con mención Cum Laude en Historia del Arte por la Universidad de Oviedo. Su obra es multidisciplinar, abarcando pintura, escultura, grabado y fotografía.', 'Arte contemporáneo/Figuración abstracta', '/assets/imagenes/artistas/Encarna.jpg'),
(3, 'Trinidad', 'Fernández', '1931-09-23', '2022-01-19', 'Avilés, Asturias', 'Fue una relevante pintora española, pionera en la transición de la figuración a la abstracción dentro del panorama artístico asturiano de posguerra. comenzó su formación de manera autodidacta en Gijón. Fue discípula y posteriormente esposa del escultor y pintor Joaquín Rubio Camín, con quien se trasladó a Madrid en 1954. Su obra se caracteriza por una evolución constante de la figuración a la abstracción.', 'Figuración/Abstracción', '/assets/imagenes/artistas/TrinidadFdez.jpg'),
(4, 'Nicanor', 'Piñole', '1878-01-06', '1978-01-18', 'Gijón, Asturias', 'Pintor español de estilo impresionista y costumbrista, clave en la renovación del arte asturiano del siglo XX. Estudió en la Escuela de San Fernando de Madrid bajo la tutela de maestros como Carlos de Haes y Antonio Muñoz Degrain y también en Roma y París, donde recibió influencias de pintores como Velázquez, Goya o Whistler. Su obra refleja escenas populares asturianas y retratos, con una paleta de colores sobria y un dibujo riguroso', 'Costumbrismo/Impresionismo', '/assets/imagenes/artistas/NicanorPinole.jpg'),
(5, 'Evaristo', 'Valle', '1873-07-11', '1951-01-29', 'Gijón, Asturias', 'Fue un pintor y escritor español, considerado una de las figuras más originales y renovadoras del arte asturiano de la primera mitad del siglo XX. A los diez años se trasladó a Puerto Rico con su padre. A su regreso, trabajó como litógrafo ilustrando revistas gráficas como Blanco y Negro y Madrid Cómico. Sufrió de agorafobia, condición que se agravó tras la muerte de su madre en 1912. Durante sus años de encierro, se dedicó también a la escritura de novelas y teatro. Su pintura es conocida por su colorismo refinado y su originalidad al retratar paisajes y tipos populares asturianos, centrándose en la vida rural, tipos campesinos, escenas de mar y carnavales.', 'Costumbrismo/Expresionismo', '/assets/imagenes/artistas/EvaristoValle.jpg'),
(6, 'Joaquín', 'Rubio Camín', '1929-09-11', '2007-12-28', 'Gijón, Asturias', 'Fue un artista asturiano polifacético, considerado uno de los escultores más relevantes de la segunda mitad del siglo XX y uno de los pocos creadores en obtener el Premio Nacional en tres disciplinas distintas: pintura, escultura y fotografía. Se formó inicialmente como pintor, influenciado por maestros locales como Evaristo Valle y Nicanor Piñole. A partir de los años 60, su obra giró hacia la escultura abstracta y constructivista, utilizando materiales como hierro, madera, piedra y mármol. Su obra se caracteriza por el rigor geométrico y la exploración del espacio, evolucionando desde la figuración hacia un constructivismo sobrio.', 'Abstracción geométrica', '/assets/imagenes/artistas/JoaquinRubioCamin.jpg'),
(7, 'Antonio', 'Suárez', '1923-01-26', '2013-10-21', 'Gijón, Asturias', 'Inició su relación con el mundo artístico en el estudio del arquitecto gijonés Antonio Álvarez Hevia donde se preparaba para delineante junto a Joaquín Rubio Camín. En 1957, fue uno de los miembros fundadores del Grupo El Paso, colectivo que introdujo el informalismo y la modernidad en la España de la posguerra. Su obra comenzó con una base figurativa que derivó hacia una abstracción muy personal. Evolucionó desde los tonos oscuros característicos de su etapa en El Paso hacia una pintura de colores luminosos, suaves y una marcada lírica visual.', 'Informalismo', '/assets/imagenes/artistas/AntonioSuarez.jpg'),
(8, 'Jose María', 'Navascués Martínez-Azcoitia', '1934-09-20', '1979-11-11', 'Madrid', 'Fue un artista y escultor fundamental para la vanguardia asturiana y española de la segunda mitad del siglo XX. Nacido en Madrid, a los cuatro años se traslada junto con su familia a Gijón. En 1952 regresa a la capital para estudiar Ingeniería de Caminos, Canales y Puertos, y, un año después comenzaría Arquitectura, pero abandonaría ambas. A mediados de los 50, regresa a Gijón dende se nicia en la pintura, pero su preocupación por las formas anatómicas y los procesos vitales le conducen a la escultura, escogiendo la madera como materia primigenia.', 'Abstracción lírica', '/assets/imagenes/artistas/Navascues.jpg'),
(9, "Mariano", "Moré Cors", "1899-05-07", "1974-07-01", "Gijón, Asturias", "Fue un pintor e ilustrador asturiano, figura clave de la pintura regionalista asturiana del siglo XX junto a artistas como Evaristo Valle y Nicanor Piñole. Nació en una familia vinculada a las artes gráficas; su padre era el dueño de dos empresas de litografía. Inició sus estudios artísticos con Nemesio Lavilla en el Ateneo Obrero de Gijón y posteriormente se trasladó a Madrid en 1917 para formarse en el taller del maestro Cecilio Plá. Su obra evolucionó desde un realismo de fuerte compromiso social hacia una visión más idealizada del paisaje y las costumbres asturianas. En su etapa temprana, retrató la dureza del trabajo industrial y minero con una estética moderna y expresiva. Tras la Guerra Civil, su estilo se suavizó, centrándose en romerías, escenas campesinas y paisajes costeros de Asturias.", "Costumbrismo/Realismo", "/assets/imagenes/artistas/MarianoMore.jpg"),
(10, 'Pelayo', 'Ortega', '1956-05-01', null, 'Mieres, Asturias', 'Es un destacado pintor y grabador asturiano, referente de la generación que revitalizó la pintura española en los años 80. Comenzó su formación en la Agrupación Gijonesa de Bellas Artes y la Escuela de Artes y Oficios de Oviedo. En 1975 se trasladó a Madrid, donde profundizó en el grabado. Su obra se caracteriza por una síntesis entre la figuración y la abstracción, con un estilo poético y a menudo minimalista.', 'Arte contemporáneo/Figuración abstracta', '/assets/imagenes/artistas/PelayoOrtega.jpg');

-- ============================================
-- 3. Insertar Museos
-- ============================================

INSERT INTO museos (id, nombre, direccion, ciudad, latitud, longitud, horario, web, imagen) VALUES

(1, 'Museo de Bellas Artes de Asturias', 'C. Sta. Ana, 1-3', 'Oviedo', 43.3617249, -5.8436432, 'HORARIO DE INVIERNO: Martes a Viernes de 10:30 a 14:00 y de 16:30 a 20:30 h. Sábados de 11:30 a 14:00 y de 17:00 a 20:00 h. Domingos y festivos de 11:30 a 14:30 h. HORARIO DE VERANO (JULIO Y AGOSTO): Martes a Sábados de 10:30 a 14:00 y de 16:00 a 20:00 h. Domingos y festivos de 10:30 a 14:30 h. CERRADO: Todos los lunes del año. 1 de enero, 1 de mayo, 21 de septiembre, 1 de noviembre, 24, 25 y 31 de diciembre.', 'https://www.museobbaa.com/', '/assets/imagenes/museos/museoBellasArtes.jpg'),
(2, 'Museo Casa Natal de Jovellanos', 'Plazuela de Jovellanos, s/n', 'Gijón', 43.545865, -5.66247, 'HORARIO: Martes a viernes: 9.30 a 14.00 y de 17.00 a 19.30 h. Sábados, domingos y festivos: de 10.00 a 14.00 y de 17.00 a 19.30 h. CERRADO: Todos los lunes del año. 1 y 6 de enero, martes de carnaval, 15 de agosto, 24, 25 y 31 de diciembre.', 'https://www.gijon.es/es/directorio/museo-casa-natal-de-jovellanos', '/assets/imagenes/museos/museoJovellanos.jpg'),
(3, 'Museo Evaristo Valle', 'Cam. de Cabueñes, 261', 'Gijón', 43.533563, -5.617563, 'HORARIO DE INVIERNO (OCTUBRE-MARZO): Martes a viernes, de 11.00 a 14.00 h. Sábados, domingos y festivos de 12.00 a 14.00 h. ABRIL, MAYO, JUNIO Y SEPTIEMBRE: Martes a viernes, de 11.00 a 14.00 h. Sábados, de 17.00 a 20.00 h. Domingos y festivos de 12.00 a 14.00 h. JULIO Y AGOSTO: Martes a sábados, de 17.00 a 20.00 h. Domingos y festivos de 12.00 a 14.00 h. CERRADO: Todos los lunes del año. 1 y 6 de enero y 24, 25 y 31 de diciembre.', 'https://evaristovalle.com/', '/assets/imagenes/museos/museoEvaristoValle.jpg'),
(4, 'Museo Nicanor Piñole', 'Pl. de Europa, 28', 'Gijón', 43.538115, -5.664059, 'HORARIO: Martes a viernes: 9.30 a 14.00 y de 17.00 a 19.30 h. Sábados, domingos y festivos: de 10.00 a 14.00 y de 17.00 a 19.30 h. CERRADO: Todos los lunes del año. 1 y 6 de enero, martes de carnaval, 15 de agosto, 24, 25 y 31 de diciembre.', 'https://www.gijon.es/es/directorio/museo-nicanor-pinole', '/assets/imagenes/museos/museoPinole.jpg'),
(5, 'Museo Barjola', 'C. Trinidad, 17', 'Gijón', 43.544187, -5.663812, 'HORARIO: Martes a sábado: 11:30 a 13:30 y de 17:00 a 20:00 h. Domingos y festivos: de 12:00 a 14:00 h. CERRADO: Todos los lunes del año.', 'https://www.museobarjola.es', '/assets/imagenes/museos/museoBarjola.jpg'),
(6, 'Centro de Escultura de Candás Museo Antón', 'C. Valdés Pumarino, 2', 'Candás', 43.5928421, -5.7635522, 'HORARIO DE INVIERNO (OCTUBRE-MAYO): Martes a viernes: de 17.30 a 19.30 h. Sábados y domingos: de 12 a 14 y de 17.30 a 19.30 h. HORARIO DE VERANO(JUNIO-SEPTIEMBRE): Martes a domingo, de 12 a 14 y de 18 a 21 h. CERRADO: Todos los lunes del año. 1 y 6 de enero y 24, 25 y 31 de diciembre.', 'https://www.museoanton.com', '/assets/imagenes/museos/museoAnton.jpg'),
(7, 'Centro de Recepción e Interpretación del Prerrománico Asturiano', 'Antiguas Escuelas del Naranco, s/n', 'Oviedo', 43.378641, 5.867362, 'HORARIO: Del 1 al 28 de febrero y del 1 de noviembre al 31 de diciembre: De miércoles a domingo, de 09:30 a 14:30 h. Del 1 de marzo al 30 de junio y del 1 de septiembre al 31 de octubre: De miércoles a domingo, de 09:30 a 13:30 y de 15:30 a 18:00 h. Del 1 de julio al 31 de Agosto: Abierto todos los días, de 09:30 a 13:30 y de 15:30 a 19:00 h. CERRADO: Del 1 al 31 de enero. Todos los lunes y martes del año, 24, 25 y 31 de diciembre.', 'https://www.centroprerromanicoasturiano.com/es/', '/assets/imagenes/museos/centroInterpretacion.jpg'),
(8, 'LABoral Centro de Arte y Creación Industrial', 'Los Prados, 121', 'Gijón', 43.524528, -5.611705, 'HORARIO: Martes a viernes: de 10:00 a 19:30 h. Sábados: de 11:00 a 19:30 h. CERRADO: Lunes y martes (excepto festivos), 1 y 6 de enero, 1 de mayo, 24, 25 y 31 de diciembre.', 'https://www.laboralcentrodearte.org/', '/assets/imagenes/museos/laboral.jpg'), 
(9, 'Centro Niemeyer', 'Av. del Zinc, s/n', 'Avilés', 43.557443, -5.91771, 'HORARIO DE INVIERNO (SEPTIEMBRE-MAYO): Miércoles a domingo: de 10:30 a 14:30 y de 15:30 a 19:30 h. HORARIO DE VERANO (JUNIO-SEPTIEMBRE): Miércoles a domingo: de 10:00 a 14:30 y de 15:30 a 20:30 h. CERRADO: 1 de enero, 24, 25 y 31 de diciembre.', 'https://centroniemeyer.es/', '/assets/imagenes/museos/centroNiemeyer.jpg');

-- ============================================
-- 4. Insertar Monumentos Prerrománicos
-- ============================================

INSERT INTO monumentos (id, id_epoca, nombre, fecha_construccion, descripcion, latitud, longitud) VALUES

(1, 1, 'San Tirso', 'Siglo IX', 'Fundada por Alfonso II "El Casto" como parte del complejo episcopal ovetense. Aunque sufrió múltiples reformas a lo largo de los siglos (la fachada actual es barroca), conserva de su origen prerrománico el testero de la capilla mayor con su emblemática ventana trigeminada de arcos de medio punto y columnas con capiteles de tradición visigoda. Esta ventana tripartita se ha convertido en uno de los iconos visuales del arte asturiano.', 43.3627, -5.8436),
(2, 1, 'Cámara Santa', 'Siglo IX', 'Capilla palatina de dos pisos integrada en la Catedral de Oviedo. El nivel superior (San Miguel) custodia las reliquias del Arca Santa y las cruces de la Victoria y los Ángeles, símbolos de Asturias. El espacio inferior (Santa Leocadia) presenta bóveda de cañón. Destaca el apostolado románico añadido posteriormente en sus columnas, siendo uno de los conjuntos escultóricos más importantes del siglo XII en España. Originalmente formaba parte del complejo palatino de Alfonso II.', 43.3625, -5.8431),
(3, 1, 'San Julián de los Prados', '812-842', 'Conocida popularmente como Santullano, es la iglesia más grande y antigua del periodo asturiano. Mandada construir por Alfonso II, destaca por su gran amplitud, su planta basilical con transepto elevado y, sobre todo, por su excepcional ciclo de pinturas murales anicónicas con motivos geométricos y arquitectónicos que cubrían originalmente todos sus muros interiores. Este programa pictórico, único en Europa occidental de su tiempo, evidencia la continuidad con la tradición pictórica romana tardía.', 43.3670, -5.8368),
(4, 1, 'Santa María de Bendones', 'Siglo IX', 'Estructura basilical que sigue el modelo de Santullano, aunque con dimensiones más reducidas. Fue reconstruida tras sufrir graves daños en 1936 durante la Guerra Civil española. Destaca su gran nártex o pórtico de entrada y la disposición de sus naves laterales, siendo un ejemplo clave del estilo bajo el reinado de Alfonso II. Su ubicación en una colina domina el valle, mostrando la importancia estratégica y simbólica de estas edificaciones.', 43.3364, -5.8039),
(5, 1, 'San Pedro de Nora', 'Siglo IX', 'Iglesia de planta basilical con tres naves separadas por arquerías de medio punto sobre pilares cuadrados, situada en un entorno rural junto al río Nora. Presenta una cabecera tripartita característica del arte asturiano. A pesar de su reconstrucción casi completa tras la Guerra Civil (dirigida por el arquitecto Luis Menéndez Pidal), mantiene la pureza de líneas y la sobriedad volumétrica características del arte asturiano.', 43.3644, -5.9525),
(6, 2, 'Santa María del Naranco', '842', 'Obra maestra del periodo ramirense. Originalmente concebida como un aula regia o palacio de recreo situado en las afueras de Oviedo, fue transformada en iglesia en el siglo XII. Destaca por su arquitectura innovadora: estructura rectangular de dos pisos con miradores laterales (belvederes) con arquerías ciegas, bóveda de cañón reforzada con arcos fajones interiores y exquisita decoración escultórica en medallones y capiteles. Representa la cúspide de la arquitectura prerrománica europea.', 43.3790, -5.8659),
(7, 2, 'San Miguel de Liño/Lillo', '848', 'Iglesia palatina dedicada a San Miguel Arcángel, situada a escasos metros del Naranco y complementaria a este complejo regio. Actualmente solo se conserva el tercio occidental de la planta original tras un derrumbe en el siglo XIII. Es famosa por la esbeltez de sus proporciones, sus celosías de piedra tallada con motivos geométricos (algunas únicas en el arte europeo) y los relieves de sus jambas inspirados en un díptero consular romano, testimonio del interés por el pasado clásico.', 43.3808, -5.8672),
(8, 2, 'Santa Cristina de Lena', '852', 'Pequeña joya del periodo ramirense situada sobre una colina con vistas panorámicas al valle del Caudal. Su elemento más distintivo es el iconostasio: una triple arquería de piedra que separa el presbiterio de la nave, decorada con piezas visigodas reutilizadas. Esta estructura, junto con las tribunas laterales, crea un espacio jerarquizado destinado a la liturgia. Su planta de cruz griega y la calidad de su sillería evidencian la madurez del estilo ramirense.', 43.1275, -5.8144),
(9, 3, 'Foncalada', 'Siglo IX', 'Única construcción civil de utilidad pública del prerrománico asturiano que se conserva in situ. Se trata de una fuente de agua potable protegida por un templete de piedra con arco de medio punto. En el frontón luce la Cruz de la Victoria junto con su inscripción. Aunque tradicionalmente se atribuye al reinado de Alfonso III, estudios recientes apuntan a que su construcción pudo realizarse bajo Alfonso II, sin descartar por completo la hipótesis de un origen romano. Es un testimonio excepcional de la ingeniería hidráulica altomedieval.', 43.3653, -5.8455),
(10, 3, 'Santo Adriano de Tuñón', '891', 'Iglesia de estructura sencilla y planta basilical de tres naves, mandada construir por Alfonso III y su esposa Jimena como parte de un monasterio. Conserva importantes restos de pinturas murales originales en la cabecera con motivos geométricos y vegetales. Es un testimonio fundamental de la expansión del reino asturiano hacia los valles interiores de la cordillera Cantábrica y del patrocinio regio en la repoblación de territorios.', 43.2925, -5.9814),
(11, 3, 'San Salvador de Valdediós', '893', 'Denominada popularmente "El Conventín", representa el apogeo del periodo alfonsino. Situada en un entorno natural exuberante del valle de Boides, forma parte de un monasterio más amplio. Destaca por su pórtico lateral abierto (único en el prerrománico asturiano) y por mantener un equilibrio perfecto entre la influencia asturiana tradicional y los nuevos elementos mozárabes introducidos por artesanos procedentes de Al-Ándalus, anunciando el arte románico.', 43.4375, -5.4686),
(12, 3, 'San Salvador de Priesca', '921', 'Consagrada en el siglo X, sigue fielmente los esquemas arquitectónicos de Santullano aunque en una época más tardía. Conserva una notable parte de su decoración pictórica original en los muros interiores y arcos triunfales, con influencias mozárabes. Sirve de eslabón de transición entre el estilo asturiano puro y el primer románico que empezaba a gestarse en el resto de Europa, manteniendo la tradición local mientras incorpora novedades del nuevo estilo por venir.', 43.4897),
(13, 3, 'Santiago de Gobiendes', 'c. 921', 'Situada cerca de la costa oriental asturiana (Colunga), esta iglesia de considerables dimensiones destaca por la elegancia de sus proporciones y la calidad de su fábrica de sillarejo. Fue profundamente reformada en el siglo XVIII (añadiéndose una espadaña barroca), pero las restauraciones modernas han permitido recuperar su esencia prerrománica, especialmente visible en la cabecera tripartita y los capiteles decorados con motivos vegetales y geométricos.', 43.4731, -5.2289);

-- ============================================
-- 5. Insertar Obras
-- ============================================
INSERT INTO obras (id_artista, id_museo, id_epoca, titulo, fecha_creacion, tecnica, descripcion, dimensiones) VALUES

-- JULIA ALCAYDE
(1, 2, 8, 'Bodegón de la caza', 1897, 'Óleo sobre lienzo', 'Los animales y objetos que componen esta naturaleza muerta presentan un tratamiento muy detallado, mientras que en el paisaje la pincelada se hace más suelta y la composición más abocetada. En un primerísimo plano se acumulan las capturas (liebres y perdices) junto con los objetos propios del cazador: el zurrón, la navaja y el cuerno, en una clara intención de dotar de inmediatez y realismo a la composición.', '98 x 134 cm'),
(1, 2, 8, 'El puesto de mi calle', 1899, 'Óleo sobre lienzo', 'Representación de un bodegón en un paisaje rural de luz clara. En la composición alcanza un gran protagonismo la pared de la casa rústica del fondo, en la que se aprecian las calidades de las diferentes capas del encalado, la teja y la madera. En primer término, sitúa una completa naturaleza muerta que incluye frutas, hortalizas y piezas de caza, junto a cestas con diferentes clases de mimbres y objetos de metal como una balanza romana y una jarra. Ganó la segunda medalla en la Exposición Nacional de Bellas artes de 1899.', '143,5 x 109 cm'),
(1, null, 8, 'Frutas', 1911, 'Óleo sobre lienzo', 'Aunque el paisaje juega siempre un papel muy importante en todas sus obras, en ésta, ha recortado el escenario a la mínima expresión, concentrándose en el primer plano de los elementos de la composición. El bodegón, compuesto por melocotones, uvas, ciruelas y granadas debió de ser realizado en torno al mes de septiembre, cuando la cosecha puede hacer coincidir esas existencias. La autora se concentra en la representación de las texturas diferentes de las frutas a través de una descripción realista. La pintora lo envió junto con tres obras más a la Exposición Nacional de Bellas Artes de 1912, ganando de nuevo una segunda medalla.', '72 x 106 cm'),
(1, null, 8, 'Flores', 1920, 'Grafito y albayalde sobre papel', 'Dibujo monocromático que representa detalladamente varias flores, identificables visualmente como lirios o azucenas. La composición es vertical y muestra un tallo principal del que brotan varias flores en distintas etapas de floración, desde capullos cerrados hasta flores completamente abiertas. El uso del albayalde (blanco), junto con el grafito demuestra el gran dominio del sombreado y las texturas para dar volumen a pétalos y hojas.', '41 x 25 cm'),

-- ENCARNA
(2, null, 12, 'En el fondo', 1986, 'Técnica mixta', 'Sus primeras obras son composiciones de colorido vibrante, intenso pero armonioso, con unas amplias manchas de color en técnica mixta, con unos títulos que enuncian palabras o principios de frases inacabadas.', '100 x 80 cm'),
(2, null, 12, 'De su capa un sayo', 2003, 'Bronce', 'La escultura ocupa un espacio de mucho interés para la artista, aunque no accede a ella hasta 2003. De su primera etapa destacan los torsos en bronce, de tendencia abstracta sin perder la figuración con una pátina verdinegra. Destaca la riqueza de planos en composiciones sin perder la sensualidad de las formas y la variedad de puntos de vista, que en ocasiones evocan la antigua estatuaria egipcia.', '17 x 5 cm'),
(2, null, 12, 'Armando Barbón', 2005, 'Bronce y acero cotén', 'Realizada en homenaje al joven futbolista fallecido en un accidente de tráfico en 2003. La autora imaginó al jugador entre velado y presente, en movimiento, un movimiento que se aprecia desde cualquier punto de vista desde el que se observe la obra. Consiste en un torso conceptualmente clasicista en el fondo y un toque realista, utilizando la técnica del paño mojado. Sin piernas, sugiere la imposibilidad de movimiento real, con el rostro en tensión y sereno a la vez. El emplazamiento, escogido por la familia y la propia autora, favorece esa sensación de movimiento, ya que parece que Armando está a punto de saltar al campo desde la esquina de la grada más joven de aficionados del Real Oviedo.', '75 x 50 x 40 cm'),
(2, null, 12, 'Alto vuelo', 2007, 'Técnica mixta', 'Esta es una de las obras que forma parte de un conjunto denominado cajas de luz. En esta serie, juega con un doble juego de visiones, con luz y sin ella. Pasan de la pintura plana a las tenues escenas de toque surreal, donde se pueden ver siluetas de figuras humanas, peces o pájaros, como en este caso.', '38 x 38 cm'),
(2, null, 12, 'Lengua antigua', 2013, 'Técnica mixta sobre lienzo', 'Este año marca el inicio de una fuerte inspiración del arte de Méjico, lugar donde terminó sus estudios de Historia del Arte. Este es uno de los 36 lienzos realizas en esta época. En ésta, se ve la cabeza y el cuerpo de Balam, el jaguar, animal totémico y deidad presente en la cultura maya que representa el Sol en su paso por el ultramundo. Los fríos azulados y verdes con los contrapuntos amarillos, rojos y ocres consiguen una atmosfera abstracta en contraste con la clara definición del ojo y perfil de la cabeza del animal.', '145 x 145 cm'),

-- TRINIDAD FERNANDEZ
(3, 2, 9, 'Montmartre', 1957, 'Óleo sobre lienzo', 'Esta obra es un ejemplo de la etapa figurativa de la artista, con una simplificación y esquematización de las formas. A menudo se ha descrito su obra de este periodo con rasgos de un "toque ingenuo o infantil". La pintura captura una escena urbana del célebre barrio de Montmartre en París, conocido por haber sido el epicentro de la vida artística a finales del siglo XIX y principios del XX. La composición muestra edificios con una perspectiva marcada, un cielo nublado y una figura solitaria caminando por la calle, evocando una atmósfera introspectiva.', '30 x 70 cm'),
(3, null, 9, 'Luarca', 1960, 'Óleo sobre lienzo', 'Paisaje portuario asturiano, pertenece a su período figurativo. La obra representa el puerto de Luarca, con casas escalonadas en una colina y botes en el agua, capturando la esencia de la costa asturiana. ', '92 x 73 cm'),
(3, null, 9, 'Paisaje asturiano', 1990, 'Óleo sobre lienzo', 'Simplificación de las formas naturales (montañas, valles o prados) utilizando tonalidades que evocan la atmósfera de Asturias (verdes, grises, ocres), aplicados con una técnica que prioriza la textura y la vibración de la materia.', '35 x 27 cm'),
(3, null, 9, 'La noche', 2012, 'Óleo sobre lienzo', 'En esta obra, explora la transición entre la realidad y la abstracción bajo condiciones lumínicas de penumbra. A diferencia de sus paisajes luminosos de Asturias o Luarca, en esta temática Fernández utiliza la oscuridad para desdibujar los límites de los objetos. una paleta dominada por azules profundos, negros y grises, a menudo interrumpidos por sutiles puntos de luz', '80 x 80 cm'),

-- PIÑOLE
(4, 4, 8, 'La vuelta de la Romería', 1915, 'Óleo sobre lienzo', 'Representa la vuelta al atardecer de la Romería que se celebra en Perlora cada primer domingo del mes de agosto, con los romeros subiendo campo a través hacia la aldea de Prendes. La línea serpenteante de figuras se enmarca en una amplia panorámica de la costa del concejo de Carreño, con el cabo de San Antonio de Candás a la izquierda. La escena se desarrolla bajo una luz difusa, en un atardecer oscurecido de cielo encapotado que da cierto dramatismo al conjunto.', '119,5 x 178,5 cm'),
(4, 1, 8, 'Recogiendo la manzana', 1922, 'Óleo sobre lienzo', 'La escena tiene lugar al final del verano en Carreño, en la empinada pomarada de la Quinta de Chor, finca en la que pasaron sus veranos la familia Prendes. En la pintura, de gran formato, un grupo de vecinos colaboran voluntariamente para completar la tarea de la recogida de la manzana. Un amplio número de hombres, mujeres y niños, incluidos los sobrinos de Nicanor, se afanan en recolectar y transportar la fruta al lagar y comenzar el proceso de trasformación en sidra natural. Al fondo, bajo un cielo poblado de nubes, se observa una característica casa mariñana.', '150 x 206 cm'),
(4, 4, 8, 'Cervera', 1938, 'Óleo sobre lienzo', 'Representa la destrucción y las víctimas causadas durante el asedio de Gijón por el crucero Almirante Cervera que bombardeó la ciudad de forma continuada, desencadenando el pánico entre la población. El "Cervera" simboliza la muerte que avanza sobre el caos y la destrucción, en un desorden del que nadie se salva. La industria, las artes, todo queda destruido por el avance de esa figura cadavérica y fantasmagórica.', '86,5 x 106 cm'),
(4, 4, 8, 'Retrato de doña Brígida Rodríguez Prendes', 1916, 'Óleo sobre lienzo', 'El fuerte vínculo que unía al artista con su madre tiene su reflejo en el importante número de retratos que le hace a lo largo de su vida, siendo el último el que le pintó en 1951, poco antes de morir. En esta obra, el pintor deja constancia de su extraordinaria capacidad para captar la personalidad de su madre, mujer de carácter fuerte y austero. Sentada en una sólida butaca tapizada en capitoné, Brígida está sobriamente vestida y mantiene una expresión seria y firme. Su figura se recorta sobre un fondo neutro que centra toda la atención en las manos y en el rostro, fuertemente iluminados por una luz indeterminada de tradición velazqueña que envuelve la figura.', '95 x 89 cm'),
(4, 4, 8, 'El viejo autobús', 1934, 'Temple sobre cartón', 'Se trata de un boceto para cartel, género que en los años veinte vivió su época dorada gracias al aumento de la demanda publicitaria y de las campañas propagandísticas institucionales. En Gijón, la celebración a partir de 1924 de la Feria de Muestras, supuso un impulso de las actividades relacionadas con la difusión de la ciudad como destino turístico. El protagonista de la composición es el autobús rojo de viajeros de la empresa del "Chamico" que cubría la línea entre Tamón y Gijón; por la izquierda, en segundo término, circulan un coche y otro autobús, ambos descapotables. La escena transcurre en un apacible entorno rural con la Campa Torres y la desaparecida playa de Aboño al fondo.', '74 x 104 cm'),

-- VALLE
(5, 2, 9, 'La promesa', 1905, 'Óleo sobre lienzo', 'Los protagonistas de la obra son una pareja de aldeanos que aparece en primer plano llevando sendas flores moradas que simbolizan su compromiso matrimonial. Se trata de dos figuras de tratamiento monumental que denotan la influencia de Zuloaga, aunque la expresión socarrona y caricaturesca de sus rostros corresponde al personal estilo de Valle. A espaldas de las figuras se despliega un paisaje asturiano iluminado por los rayos de sol que se cuelan por un celaje plagado de nubes creando diferentes zonas de luces y sombras.', '168 x 203 cm'),
(5, 3, 9, 'Pierrot', 1909, 'Óleo sobre lienzo', 'Es una de sus obras más conocidas y modificadas durante toda su carrera. La pintura muestra al personaje tradicional de la comedia del arte italiano, el Pierrot, conocido como el payaso triste que sufre por amor no correspondido, vestido con su característico traje holgado, a menudo blanco, con grandes botones, un gorro, y cuello con volantes, y maquillaje pálido. Valle retoma este arquetipo para reflejar la tensión entre lo espiritual y lo material, la melancolía y el humor, y la profundidad lírica de la vida.', '107 x 98 cm'),
(5, 3, 9, 'Cudillero', 1919, 'Óleo sobre lienzo', 'Tema recurrente en su obra, muestra una escena de pescadores en un puerto. Representa la vida portuaria con grupos de marineros charlando y trabajando, capturando la atmósfera y el carácter local de la región. La composición está dominada por una barca oscura en primer plano y un fondo de casas de pescadores apiñadas en la ladera de una colina, con toques vibrantes de velas o redes rojas.', '131 x 72 cm'),
(5, 1, 9, 'Carnavalada de Oviedo', 1928, 'Óleo sobre lienzo', 'Esta es una de las múltiples carnavaladas realizadas desde 1910 hasta el final de su vida. En ella se aprecia una serie de personajes con vestimenta de antroxu (túnicas, blusas, faldones, etc.), situados a las afueras de la ciudad de Oviedo, reconocible por la imagen de la catedral al fondo y la silueta de un monte Naranco que parece disfrazado de sierra del Aramo detrás. Distintos volúmenes cúbicos conforman un caserío que enmarca a modo de escenografía o telón de fondo la escena principal.', '111 x 127 cm'),
(5, 3, 9, 'Las tres brujas', 1945, 'Óleo sobre lienzo', 'Ramona, Xuana y Cefera fueron los nombres que Valle dio a Las tres brujas. Dos de ellas, sentadas en una muria junto a los restos de una empalizada, cuchichean entre sí; la tercera, en pie, dirige su mirada al espectador. Destaca la estructuración de la composición mediante dos diagonales que salen de las cuatro esquinas y se entrecuzan en el centro del cuadro.', '101 x 91 cm'),

-- CAMIN
(6, null, 9, 'Gasómetro', 1949, 'Óleo sobre lienzo', 'Lo aparentemente anodino de la ciudad de Gijón, fue elevado por el artista a trascendente, como el gasómetro, una estructura que se convierte en un icono simbólico de la ciudad dentro de su pintura y al que aludión constantemente a lo largo de su carrera. La pintura de temática urbana  pone en relieve y da significado a lo que a primera vista, a ojos del espectador, resultaría carente de interés o incluso desagradable. La mayoría de las veces, esta iconografía del gasómetro se encuentra acompañada de otros elementos industriales como chimeneas y naves industriales, así como los grandes muros fabriles.', '61 x 50 cm'),
(6, 1, 4, 'Salix', 1982, 'Acero', 'Las primeras esculturas en acero datan de 1961. Habrían de pasar dos o tres años alternando esta disciplina con la pintura para que esta nueva dedicación se convirtiera en exclusiva. Ya desde sus primeros compases, se observó un interés por jugar con las líneas, los volúmenes y el desarrollo espacial de sus figuras utilizando el angular o diedro como forma a partir de la que articular todas sus composiciones. El corte, la doblez y la soldadura del material se convirtieron a partir de este momento en los tres gestos técnicos utilizados por el artista. Esta obra, una interpretación abstracta de un sauce, está concebida como una sencilla concatenación de módulos, en los que todo parece estar perfectamente medido y proporcionado combinando solidez y ligereza en la misma.', '114 x 54 x 31 cm'),
(6, null, 6, 'Maternidad', 1986, 'Ladrillo tallado', 'Escultura configurada a partir de hiladas de ladrillo que posteriormente se tallaron como si se tratara de un bloque monolítico de piedra. Estableciendo conexiones con la escultura en bronce de menores dimensiones, esta Maternidad se caracteriza por ser una estructura cerrada, de marcada esencialidad formal evidenciando una dimensión trascendental dentro de su carrera escultórica.', '400 cm'),
(6, null, 4, 'Sin título', 1997, 'Acero cortén', 'Ubicada bajo el puente sobre el río Caudal, cerca del Polígono Industrial "Gonzalín", aunque no tiene nombre, es conocida popularmente como "La gaviota" o "Ave" debido a sus formas que sugieren el vuelo del animal. Forma parte de las intervenciones del artista en el paseo fluvial de Mieres al que también se le atribuye el diseño de la barandilla y los motivos ornamentales de la caja del río.', '300 x 1000 x 1400 cm'),
(6, null, 5, 'Aguadora', 2003, 'Bronce', 'Escultura realizada en bronce a tamaño natural, ubicada en el paseo fluvial entre Figaredo y Ujo. Realizada en colaboración con la empresa Bronces Artísticos S.L. Con esta escultura se busca homenajear a todas aquellas mujeres que trabajaron como aguadoras, una profesión típica de la minería del pasado siglo. Estas trabajadoras se encargaban de llevar agua a los mineros que salían de las bocaminas tras sus jornadas laborales.', '160 x 50 x 40 cm'),

-- ANTONIO SUAREZ
(7, null, 10, 'Fábrica Electra de El Llano', 1951, 'Óleo sobre lienzo', 'La pintura muestra un paisaje industrial, un tema recurrente en los inicios de su carrera. La obra tiene un estilo figurativo con un enfoque en la composición y la estructura, aunque con una pincelada expresiva y empastada. La escena está dominada por edificios de líneas rectas y formas geométricas simples. Destacan una gran chimenea industrial de ladrillo y una estructura metálica enrejada de gran tamaño que se elevan sobre las casas circundantes.', '28 x 35 cm'),
(7, null, 10, 'Tiovivo (Plaza de Europa)', 1954, 'Óleo sobre lienzo', 'Esta obra pertenece a su etapa inicial figurativa y muestra una escena urbana con un carrusel o tiovivo como protagonista central, un tema que el artista representó en otras ocasiones. La composición está dominada por las formas circulares del carrusel, con caballos blancos y rojos, y las estructuras de los árboles y los edificios de la ciudad en el fondo. Se observan dos figuras de personas con abrigos rojos en primer plano, a la izquierda de la escena.', '38 x 46 cm'),
(7, 1, 10, 'Pintura', 1958, 'Óleo sobre lienzo', 'Este cuadro, presente en la XXIX Bienal de Venecia, es un buen ejemplo de esos primeros años de singladura abstracta. Sobre un fondo dominado por gamas frías aplicadas mediante veladuras de grises, marrones y blancos, aparecen flotando un conjunto de manchas para cuya definición el artista se sirve de colores como el rojo, el azul y el dorado. Frente a la gravedad y fiereza del expresionismo abstracto de sus compañeros de El Paso, la obra de Suárez siempre se ha caracterizado por una búsqueda de la elegancia y el refinamiento cromático.', '114 x 145 cm'),
(7, 2, 10, 'Pintura', 1960, 'Óleo sobre lienzo', 'Suárez prescinde de dar título a sus cuadros denominándolos con el genérico nombre de "pinturas". Este procedimiento permite al espectador interpretar sin condicionamientos previos lo que está observando. Así ocurre en esta obra, si bien la inscripción "El buey" que aparece al dorso, parece indicar que está inspirada en el famoso bodegón de Rembrandt "El buey desollado", con el que guarda paralelismos compositivos.', '146 x 113 cm'),
(7, 2, 10, 'Sin título', 1960, 'Óleo sobre lienzo y madera', 'Composición netamente abstracta aunque con sugerencias orgánicas. Sobre un fondo de gamas frías dispone la materia pictórica a partir de un núcleo central dejando los márgenes de la tela libres. La paleta de colores es reducida: blancos, grises y sobre todo tostados y cobrizos aplicados con veladuras que dotan de volumen a la composición. En este caso introduce pequeños tacos de madera que, dispuestos en línea, evocan fragmentos de mosaicos.', '140 x 194,5 cm'),

-- NAVASCUÉS
(8, 2, 5, 'Hamaca', 1974, 'Madera de pino encolada', 'Primera de un grupo de obras de gran tamaño que funcionan a manera de grandes artefactos con significados contrapuestos. Un objeto lúdico y relacionado con el descanso adquiere un sentido diferente al suspenderlo de dos sogas sostenidas por ganchos de carnicero. Además, al cubrirlo con una tapa, le da un aspecto de ataúd. Esta idea se refuerza por la existencia de unos relieves en la cubierta que sugieren la presencia de una figura atrapada luchando por salir.', '172 x 320 x 60 cm'),
(8, 2, 5, 'Fórmula 1', 1975, 'Madera de pino encolada', 'Navascués mostró un acusado interés por el mundo y la iconografía automovilística, realizando esculturas en las que representa la figura tanto de un piloto como de los cascos de protección. Además, en muchos de sus dibujos los accidentes y la velocidad son el tema sobre el que gira la representación. En esta obra el núcleo central es la figura de un piloto que cuelga, mediante cinturones de cuero, de la barra curva del bastidor de un coche de carreras.', '110 x 118 x 320 cm'),
(8, 2, 5, 'Avión', 1975, 'Madera de pino encolada', 'Obra monumental que destaca entre los grandes artefactos escultóricos realizados por el autor. El elemento central es la figura del piloto y las dos piezas troncopiramidales que se corresponden con las manos, produciendo un híbrido entre la forma humana y la máquina.', '210 x 372 x 109 cm'),
(8, 2, 5, 'Caja para matar vampiros', 1975, 'Madera de pino encolada y cuero', 'Entre los años 1974 y 1975 el artista crea un conjunto de piezas (guillotinas, armas y objetos inspirados en la iconografía vampírica) cuyo significado gira en torno a la idea de la muerte aunque despojada de todo dramatismo mediante el empleo de la ironía. En el interior de esta caja, Navascués dispone unos alojamientos forrados en cuero que contienen una estaca y un martillo para matar vampiros, enfrentando el carácter trágico de su significado con la belleza formal y la delicada ejecución de toda la pieza.', '30 x 20 x 63 cm'),
(8, 1, 5, 'Piloto', 1975, 'Madera de pino encolada y cuero', 'En esta "cápsula antropomórfica" se muestra solamente el "envoltorio" del piloto de carreras: el casco y el traje, algo, por tanto, fragmentario y descontextualizado. Para él, "el piloto de coches de carrera es, a la vez, conductor y conducido. Dominador y atrapado. Abierto y encerrado. Su imagen externa es su propia imagen virtual".', '75 x 49 x 39 cm'),

-- MORÉ
(9, null, 11, 'Niño de la cuenca / Y llegará a ser hombre', 1927, 'Óleo sobre lienzo', 'Obra de temática social que retrata la dura realidad del trabajo minero en Asturias durante las décadas de 1920 y 1930. El artista capta el ambiente de las explotaciones y la militancia social de la época. La atención se centra en el rostro de un joven carbonero, visiblemente agotado, que apenas puede mantener la mirada. Un gran saco sobre su cabeza ocupa la mayor parte del primer plano. El fondo montañoso se difumina en la composición, mientras que a la derecha se insinúa la silueta de una instalación fabril.', '100 x 101 cm'),
(9, null, 11, 'Mineros asturianos', 1928, 'Óleo sobre lienzo', 'La escena muestra a cuatro mineros caminando de frente, con la fatiga reflejada en sus rostros y posturas. Llevan ropa de trabajo oscura y algunos portan paraguas cerrados, lo que sugiere un día lluvioso o grisáceo. El fondo presenta un paisaje industrial y semiurbano. Se observan casas, una chimenea humeante y una montaña.', '100 x 101 cm'),
(9, null, 11, 'Derechos del niño', 1928, 'Litografía sobre papel', 'Cartel propagandístico en favor de los derechos infantiles, del Instituto de Puericultura de Gijón. Lámina ilustrada con un bebé detrás de una tribuna y, al fondo, niños con pancartas reclamando sus derechos.', '83 x 60 cm'),
(9, null, 11, 'Costa cantábrica', 1945, 'Óleo sobre lienzo', 'Después de la Guerra Civil española, la visión de la realidad asturiana queda mucho más dulcificada y la figuración tiende a la geometrización. El artista dota a la obra de un dramatismo contenido al situar a esas cuatro mujeres en primer plano y de espaldas al espectador, dirigiendo su mirada hacia un mar de oleaje amenazante, que no presagia buenas noticias para las embarcaciones que puedan estar faenando.', '132 x 169 cm'),
(9, 2, 8, 'Pescadoras de Lastres', 1948, 'Óleo sobre lienzo', 'Visión de la realidad asturiana más dulcificada, tendiendo hacia el costumbrismo. La composición es monumental y se enfoca en la narrativa visual de las mujeres, que avanzan en una procesión ascendente por un camino empinado. Las figuras están dispuestas en primer plano, guiando la mirada del espectador a través de la escena. Al fondo, se observan las tradicionales casas marineras encaladas, con ropa tendida en los balcones, y un paisaje montañoso y costero detallado.', '163 x 209 cm'),

-- PELAYO ORTEGA
(10, null, 12, 'Taller nocturno', 2007, 'Óleo y acrílico sobre lienzo', 'El centro está ocupado por una maraña de trazos amarillos brillantes que parecen una jaula, un andamio o una estructura caótica. Una lámpara colgante ilumina intensamente la estructura central y los objetos circundantes contra un fondo negro, creando un fuerte contraste y una atmósfera dramática e íntima, típica de un taller o escena nocturna. En la parte inferior se ven dos sillas, una con asiento amarillo y respaldo azul claro, y otra con asiento rojo y respaldo azul. A la derecha, la silueta esquemática de una figura humana de espaldas introduce un elemento narrativo. La obra evoca la soledad, el tránsito y la reflexión, temas recurrentes en la producción de Ortega, donde el espectador es invitado a ser protagonista de la escena.', '100 x 70 cm'),
(10, null, 12, 'Que le parta un rayo', 2013, 'Litografía', 'Es una litografía en la que Pelayo Ortega despliega su estilo característico, mezclando elementos figurativos y abstractos con una estética que recuerda al cómic y al Pop Art. La obra presenta una composición de planos de color planos y líneas negras gruesas que delimitan las formas. El color es vibrante, con áreas de amarillo, verde, rojo y gris, que contrastan fuertemente entre sí.', '58 x 76 cm'),
(10, null, 12, 'N.Y.', 2017, 'Óleo y acrílico sobre lienzo', 'La obra genera una visión escenográfica de Nueva York, tratada casi como una ilusión o un gran decorado teatral, utilizando un trabajo minucioso y lleno de sutileza. Combina la abstracción geométrica y matérica en planos de color (predominantemente grises, turquesas y anaranjados) con un elemento figurativo claro: una pequeña silueta humana de espaldas. Destaca el uso de la luz artificial, como la farola en el centro, que ilumina la escena y contrasta con los tonos más oscuros y la textura de la tela.', '150 x 150 cm'),
(10, null, 12, 'El Aleph', 2019, 'Acrílico sobre lienzo', 'Nombre sacado del cuento homónimo de mismo nombre del escritor Jorge Luis Borges. La obra está dominada por una mancha central de luz blanca intensa, de la cual emanan finas líneas y un efecto de explosión o implosión cósmica. La composición se centra en este punto de convergencia visual. El título hace referencia directa al concepto del "Aleph" de Borges: un punto místico en el que están, sin confundirse, todos los lugares del orbe vistos desde todos los ángulos. La pintura intenta representar visualmente esta visión simultánea e inabarcable del universo.', '200 x 200 cm'),
(10, null, 12, 'Dédalo', 2020, 'Acrílico sobre lienzo', 'La composición está dominada por planos de color geométricos y líneas negras gruesas que crean una estructura reticular, reminiscente de la arquitectura urbana. Presenta una paleta de colores fríos, predominantemente tonos azules, turquesas y grises, con un plano amarillo brillante que resalta en el centro. Una pequeña silueta humana de espaldas, ubicada dentro del plano central iluminado, introduce un elemento narrativo y simbólico. Esta figura, a menudo un "flâneur" o paseante enigmático, contrasta con la frialdad geométrica del entorno, evocando sensaciones de soledad, reflexión y el paso del tiempo.', '60 x 50 cm');

-- ============================================
-- 7. Insertar Imágenes
-- ============================================

INSERT INTO imagenes (url, tipo_entidad, id_entidad, es_principal, orden) VALUES

-- Monumentos
('/assets/imagenes/monumentos/San_Tirso_01.jpg', 'MONUMENTO', 1, true, 1),
('/assets/imagenes/monumentos/San_Tirso_02.jpg', 'MONUMENTO', 1, false, 2),

('/assets/imagenes/monumentos/Camara_Santa_01.jpg', 'MONUMENTO', 2, true, 1),
('/assets/imagenes/monumentos/Camara_Santa_planta.jpg', 'MONUMENTO', 2, false, 2),
('/assets/imagenes/monumentos/Camara_Santa_Leocadia.jpg', 'MONUMENTO', 2, false, 3),
('/assets/imagenes/monumentos/Camara_Santa_Interior_01.jpg', 'MONUMENTO', 2, false, 4),
('/assets/imagenes/monumentos/Camara_Santa_Interior_02.jpg', 'MONUMENTO', 2, false, 5),

('/assets/imagenes/monumentos/Santullano_01.jpg', 'MONUMENTO', 3, true, 1),
('/assets/imagenes/monumentos/Santullano_02.jpg', 'MONUMENTO', 3, false, 2),
('/assets/imagenes/monumentos/Santullano_03.jpg', 'MONUMENTO', 3, false, 3),
('/assets/imagenes/monumentos/Santullano_planta.jpg', 'MONUMENTO', 3, false, 4),
('/assets/imagenes/monumentos/Santullano_interior.jpg', 'MONUMENTO', 3, false, 5),
('/assets/imagenes/monumentos/Santullano_pinturas_01.jpg', 'MONUMENTO', 3, false, 6),
('/assets/imagenes/monumentos/Santullano_pinturas_02.jpg', 'MONUMENTO', 3, false, 7),

('/assets/imagenes/monumentos/Bendones_01.jpg', 'MONUMENTO', 4, true, 1),
('/assets/imagenes/monumentos/Bendones_02.jpg', 'MONUMENTO', 4, false, 2),
('/assets/imagenes/monumentos/Bendones_planta.jpg', 'MONUMENTO', 4, false, 3),
('/assets/imagenes/monumentos/Bendones_interior.jpg', 'MONUMENTO', 4, false, 4),

('/assets/imagenes/monumentos/Nora_01.jpg', 'MONUMENTO', 5, true, 1),
('/assets/imagenes/monumentos/Nora_02.jpg', 'MONUMENTO', 5, false, 2),
('/assets/imagenes/monumentos/Nora_03.jpg', 'MONUMENTO', 5, false, 3),
('/assets/imagenes/monumentos/Nora_planta.jpg', 'MONUMENTO', 5, false, 4),
('/assets/imagenes/monumentos/Nora_interior_01.jpg', 'MONUMENTO', 5, false, 5),
('/assets/imagenes/monumentos/Nora_interior_02.jpg', 'MONUMENTO', 5, false, 6),

('/assets/imagenes/monumentos/Naranco_01.jpg', 'MONUMENTO', 6, true, 1),
('/assets/imagenes/monumentos/Naranco_02.jpg', 'MONUMENTO', 6, false, 2),
('/assets/imagenes/monumentos/Naranco_03.jpg', 'MONUMENTO', 6, false, 3),
('/assets/imagenes/monumentos/Naranco_planta.jpg', 'MONUMENTO', 6, false, 4),
('/assets/imagenes/monumentos/Naranco_interior_01.jpg', 'MONUMENTO', 6, false, 5),
('/assets/imagenes/monumentos/Naranco_interior_02.jpg', 'MONUMENTO', 6, false, 6),
('/assets/imagenes/monumentos/Naranco_mirador.jpg', 'MONUMENTO', 6, false, 7),
('/assets/imagenes/monumentos/Naranco_cripta.jpg', 'MONUMENTO', 6, false, 8),

('/assets/imagenes/monumentos/Lillo_01.jpg', 'MONUMENTO', 7, true, 1),
('/assets/imagenes/monumentos/Lillo_02.jpg', 'MONUMENTO', 7, false, 2),
('/assets/imagenes/monumentos/Lillo_planta.jpg', 'MONUMENTO', 7, false, 3),
('/assets/imagenes/monumentos/Lillo_interior_01.jpg', 'MONUMENTO', 7, false, 4),
('/assets/imagenes/monumentos/Lillo_interior_02.jpg', 'MONUMENTO', 7, false, 5),
('/assets/imagenes/monumentos/Lillo_pintura.jpg', 'MONUMENTO', 7, false, 6),
('/assets/imagenes/monumentos/Lillo_relieve.jpg', 'MONUMENTO', 7, false, 7),

('/assets/imagenes/monumentos/Lena_01.jpg', 'MONUMENTO', 8, true, 1),
('/assets/imagenes/monumentos/Lena_02.jpg', 'MONUMENTO', 8, false, 2),
('/assets/imagenes/monumentos/Lena_03.jpg', 'MONUMENTO', 8, false, 3),
('/assets/imagenes/monumentos/Lena_planta.jpg', 'MONUMENTO', 8, false, 4),
('/assets/imagenes/monumentos/Lena_interior_01.jpg', 'MONUMENTO', 8, false, 5),
('/assets/imagenes/monumentos/Lena_interior_02.jpg', 'MONUMENTO', 8, false, 6),
('/assets/imagenes/monumentos/Lena_altar.jpg', 'MONUMENTO', 8, false, 7),

('/assets/imagenes/monumentos/Foncalada_01.jpg', 'MONUMENTO', 9, true, 1),
('/assets/imagenes/monumentos/Foncalada_02.jpg', 'MONUMENTO', 9, false, 2),
('/assets/imagenes/monumentos/Foncalada_03.jpg', 'MONUMENTO', 9, false, 3),

('/assets/imagenes/monumentos/Tuñon_01.jpg', 'MONUMENTO', 10, true, 1),
('/assets/imagenes/monumentos/Tuñon_02.jpg', 'MONUMENTO', 10, false, 2),
('/assets/imagenes/monumentos/Tuñon_03.jpg', 'MONUMENTO', 10, false, 3),
('/assets/imagenes/monumentos/Tuñon_planta.jpg', 'MONUMENTO', 10, false, 4),
('/assets/imagenes/monumentos/Tuñon_interior.jpg', 'MONUMENTO', 10, false, 5),
('/assets/imagenes/monumentos/Tuñon_pinturas.jpg', 'MONUMENTO', 10, false, 6),

('/assets/imagenes/monumentos/Valdedios_01.jpg', 'MONUMENTO', 11, true, 1),
('/assets/imagenes/monumentos/Valdedios_02.jpg', 'MONUMENTO', 11, false, 2),
('/assets/imagenes/monumentos/Valdedios_03.jpg', 'MONUMENTO', 11, false, 3),
('/assets/imagenes/monumentos/Valdedios_planta.jpg', 'MONUMENTO', 11, false, 4),
('/assets/imagenes/monumentos/Valdedios_interior_01.jpg', 'MONUMENTO', 11, false, 5),
('/assets/imagenes/monumentos/Valdedios_interior_02.jpg', 'MONUMENTO', 11, false, 6),
('/assets/imagenes/monumentos/Valdedios_pinturas.jpg', 'MONUMENTO', 11, false, 7),

('/assets/imagenes/monumentos/Priesca_01.jpg', 'MONUMENTO', 12, true, 1),
('/assets/imagenes/monumentos/Priesca_02.jpg', 'MONUMENTO', 12, false, 2),
('/assets/imagenes/monumentos/Priesca_planta.jpg', 'MONUMENTO', 12, false, 3),
('/assets/imagenes/monumentos/Priesca_interior.jpg', 'MONUMENTO', 12, false, 4),
('/assets/imagenes/monumentos/Priesca_altar.jpg', 'MONUMENTO', 12, false, 5),

('/assets/imagenes/monumentos/Gobiendes_01.jpg', 'MONUMENTO', 13, true, 1),
('/assets/imagenes/monumentos/Gobiendes_02.jpg', 'MONUMENTO', 13, false, 2),
('/assets/imagenes/monumentos/Gobiendes_planta.jpg', 'MONUMENTO', 13, false, 3),
('/assets/imagenes/monumentos/Gobiendes_interior.jpg', 'MONUMENTO', 13, false, 4),

-- OBRAS
-- Julia Alcayde
('/assets/imagenes/obras/Alcayde-bodegonCaza.jpg', 'OBRA', 1, true, 1),
('/assets/imagenes/obras/Alcayde-elPuestoDeMiCalle.jpg', 'OBRA', 2, true, 1),
('/assets/imagenes/obras/Alcayde-frutas.jpg', 'OBRA', 3, true, 1),
('/assets/imagenes/obras/Alcayde-flores.jpg', 'OBRA', 4, true, 1),
-- Encarna Díaz
('/assets/imagenes/obras/Encarna-enElFondo.jpg', 'OBRA', 5, true, 1),
('/assets/imagenes/obras/Encarna-capaSayo.jpg', 'OBRA', 6, true, 1),
('/assets/imagenes/obras/Encarna-armando1.jpg', 'OBRA', 7, true, 1),
('/assets/imagenes/obras/Encarna-armando2.jpg', 'OBRA', 7, false, 2),
('/assets/imagenes/obras/Encarna-altoVueloCL.jpg', 'OBRA', 8, true, 1),
('/assets/imagenes/obras/Encarna-altoVueloSL.jpg', 'OBRA', 8, false, 2),
('/assets/imagenes/obras/Encarna-lenguaAntigua.jpg', 'OBRA', 9, true, 1),
-- Trinidad Fernández
('/assets/imagenes/obras/Trinidad-montmartre.jpg', 'OBRA', 10, true, 1),
('/assets/imagenes/obras/Trinidad-luarca.jpg', 'OBRA', 11, true, 1),
('/assets/imagenes/obras/Trinidad-paisajeAsturiano.jpg', 'OBRA', 12, true, 1),
('/assets/imagenes/obras/Trinidad-laNoche.jpg', 'OBRA', 13, true, 1),
-- Nicanor Piñole
('/assets/imagenes/obras/Pinole-vueltaDeLaRomeria.jpg', 'OBRA', 14, true, 1),
('/assets/imagenes/obras/Pinole-recogidaManzana.jpg', 'OBRA', 15, true, 1),
('/assets/imagenes/obras/Pinole-cervera.jpg', 'OBRA', 16, true, 1),
('/assets/imagenes/obras/Pinole-donaBrigida.jpg', 'OBRA', 17, true, 1),
('/assets/imagenes/obras/Pinole-autobus.jpg', 'OBRA', 18, true, 1),
-- Evaristo Valle
('/assets/imagenes/obras/Valle-laPromesa.jpg', 'OBRA', 19, true, 1),
('/assets/imagenes/obras/Valle-pierrot.jpg', 'OBRA', 20, true, 1),
('/assets/imagenes/obras/Valle-cudillero.jpg', 'OBRA', 21, true, 1),
('/assets/imagenes/obras/Valle-carnavaladaOviedo.jpg', 'OBRA', 22, true, 1),
('/assets/imagenes/obras/Valle-3brujas.jpg', 'OBRA', 23, true, 1),
-- Camín
('/assets/imagenes/obras/Camin-gasometro.jpg', 'OBRA', 24, true, 1),
('/assets/imagenes/obras/Camin-salix.jpg', 'OBRA', 25, true, 1),
('/assets/imagenes/obras/Camin-maternidad.jpg', 'OBRA', 26, true, 1),
('/assets/imagenes/obras/Camin-sinTitulo.jpg', 'OBRA', 27, true, 1),
('/assets/imagenes/obras/Camin-aguadora01.jpg', 'OBRA', 28, true, 1),
('/assets/imagenes/obras/Camin-aguadora02.jpg', 'OBRA', 28, false, 2),
('/assets/imagenes/obras/Camin-aguadora03.jpg', 'OBRA', 28, false, 3),
('/assets/imagenes/obras/Camin-aguadora04.jpg', 'OBRA', 28, false, 4),
-- Antonio Suárez
('/assets/imagenes/obras/Suarez-fabrica.jpg', 'OBRA', 29, true, 1),
('/assets/imagenes/obras/Suarez-tiovivo.jpg', 'OBRA', 30, true, 1),
('/assets/imagenes/obras/Suarez-pinturaBA.jpg', 'OBRA', 31, true, 1),
('/assets/imagenes/obras/Suarez-pinturaJove.jpg', 'OBRA', 32, true, 1),
('/assets/imagenes/obras/Suarez-sinTitulo.jpg', 'OBRA', 33, true, 1),
-- Navascués
('/assets/imagenes/obras/Navascues-hamaca.jpg', 'OBRA', 34, true, 1),
('/assets/imagenes/obras/Navascues-formula1.jpg', 'OBRA', 35, true, 1),
('/assets/imagenes/obras/Navascues-avion.jpg', 'OBRA', 36, true, 1),
('/assets/imagenes/obras/Navascues-vampiros.jpg', 'OBRA', 37, true, 1),
('/assets/imagenes/obras/Navascues-piloto.jpg', 'OBRA', 38, true, 1),
-- Mariano Moré
('/assets/imagenes/obras/More-ninoCuenca.jpg', 'OBRA', 39, true),
('/assets/imagenes/obras/More-minerosAsturianos.jpg', 'OBRA', 40, true),
('/assets/imagenes/obras/More-cartel.jpg', 'OBRA', 41, true),
('/assets/imagenes/obras/More-costaCantabrica.jpg', 'OBRA', 42, true),
('/assets/imagenes/obras/More-pescadorasDeLastres.jpg', 'OBRA', 43, true),
-- Pelayo Ortega
('/assets/imagenes/obras/PelayoOrtega-tallerNocturno.jpg', 'OBRA', 44, true),
('/assets/imagenes/obras/PelayoOrtega-queLePartaUnRayo.jpg', 'OBRA', 45, true),
('/assets/imagenes/obras/PelayoOrtega-NY.jpg', 'OBRA', 46, true),
('/assets/imagenes/obras/PelayoOrtega-elAleph.jpg', 'OBRA', 47, true),
('/assets/imagenes/obras/PelayoOrtega-dedalo.jpg', 'OBRA', 48, true);

-- ============================================
-- Mensaje final
-- ============================================
SELECT 'Datos iniciales insertados exitosamente.' AS Mensaje;
SELECT 'Resumen:' AS Titulo;
SELECT 'Épocas:' AS Tipo, COUNT(*) AS cantidad_epocas FROM epocas
UNION ALL
SELECT 'Artistas:', COUNT(*) AS cantidad_artistas FROM artistas
UNION ALL
SELECT 'Museos:', COUNT(*) AS cantidad_museos FROM museos
UNION ALL
SELECT 'Monumentos:', COUNT(*) AS cantidad_monumentos FROM monumentos
UNION ALL
SELECT 'Obras:', COUNT(*) AS cantidad_obras FROM obras
UNION ALL
SELECT 'Imagenes:', COUNT(*) AS cantidad_imagenes FROM imagenes;