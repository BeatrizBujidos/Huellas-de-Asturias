import { Component, ChangeDetectionStrategy, signal, computed } from '@angular/core';

export interface Artista {
  id: number;
  nombre: string;
  apellidos: string;
  fechaNacimiento: string;
  fechaMuerte: string | null;
  lugarNacimiento: string;
  biografia: string;
  estilo: string;
  imagen: string;
  obraSeleccionada: number;
}

@Component({
  selector: 'app-seccion-artistas',
  standalone: true,
  imports: [],
  templateUrl: './seccion-artistas.html',
  styleUrl:'./seccion-artistas.css',
})
export class SeccionArtistas {

private readonly artistas: Artista[] = [
    {
      id: 1,
      nombre: 'Julia',
      apellidos: 'Alcayde y Montoya',
      fechaNacimiento: '1885-05-22',
      fechaMuerte: '1939-02-18',
      lugarNacimiento: 'Gijón, Asturias',
      biografia: 'Pintora española especializada en bodegones, floreros y escenas de caza, también cultivó el paisaje y el retrato. Comenzó su formación en Madrid de la mano de Manuel Ramírez, profesor de la Escuela de Artes y Oficios. Participó en las Exposiciones Nacionales de Bellas Artes, siendo galardonada con tercera medalla en las ediciones de 1892 y 1895.',
      estilo: 'Costumbrismo / Realismo',
      imagen: '/assets/imagenes/artistas/JuliaAlcayde.jpg',
      obraSeleccionada: 0,
    },
    {
      id: 2,
      nombre: 'Encarna',
      apellidos: 'Díaz Velasco',
      fechaNacimiento: '1954-06-18',
      fechaMuerte: null,
      lugarNacimiento: 'Moreda de Aller, Asturias',
      biografia: 'Destacada artista visual y doctora en Historia del Arte con una trayectoria de más de 40 años. Comenzó a experimentar con la escultura a los seis años bajo la influencia de su abuelo artesano. Su obra es multidisciplinar, abarcando pintura, escultura, grabado y fotografía.',
      estilo: 'Arte contemporáneo / Figuración abstracta',
      imagen: '/assets/imagenes/artistas/Encarna.jpg',
      obraSeleccionada: 0,
    },
    {
      id: 3,
      nombre: 'Trinidad',
      apellidos: 'Fernández',
      fechaNacimiento: '1931-09-23',
      fechaMuerte: '2022-01-19',
      lugarNacimiento: 'Avilés, Asturias',
      biografia: 'Relevante pintora española, pionera en la transición de la figuración a la abstracción dentro del panorama artístico asturiano de posguerra. Fue discípula y posteriormente esposa del escultor Joaquín Rubio Camín. Su obra se caracteriza por una evolución constante.',
      estilo: 'Figuración / Abstracción',
      imagen: '/assets/imagenes/artistas/TrinidadFdez.jpg',
      obraSeleccionada: 0,
    },
    {
      id: 4,
      nombre: 'Nicanor',
      apellidos: 'Piñole',
      fechaNacimiento: '1878-01-06',
      fechaMuerte: '1978-01-18',
      lugarNacimiento: 'Gijón, Asturias',
      biografia: 'Pintor español de estilo impresionista y costumbrista, clave en la renovación del arte asturiano del siglo XX. Estudió en la Escuela de San Fernando de Madrid y en Roma y París. Su obra refleja escenas populares asturianas y retratos, con una paleta de colores sobria y un dibujo riguroso.',
      estilo: 'Costumbrismo / Impresionismo',
      imagen: '/assets/imagenes/artistas/NicanorPinole.jpg',
      obraSeleccionada: 0,
    },
    {
      id: 5,
      nombre: 'Evaristo',
      apellidos: 'Valle',
      fechaNacimiento: '1873-07-11',
      fechaMuerte: '1951-01-29',
      lugarNacimiento: 'Gijón, Asturias',
      biografia: 'Pintor y escritor español, considerado una de las figuras más originales y renovadoras del arte asturiano de la primera mitad del siglo XX. Su pintura es conocida por su colorismo refinado y su originalidad al retratar paisajes y tipos populares asturianos.',
      estilo: 'Costumbrismo / Expresionismo',
      imagen: '/assets/imagenes/artistas/EvaristoValle.jpg',
      obraSeleccionada: 0,
    },
    {
      id: 6,
      nombre: 'Joaquín',
      apellidos: 'Rubio Camín',
      fechaNacimiento: '1929-09-11',
      fechaMuerte: '2007-12-28',
      lugarNacimiento: 'Gijón, Asturias',
      biografia: 'Artista asturiano polifacético, considerado uno de los escultores más relevantes de la segunda mitad del siglo XX y uno de los pocos creadores en obtener el Premio Nacional en tres disciplinas distintas: pintura, escultura y fotografía.',
      estilo: 'Abstracción geométrica',
      imagen: '/assets/imagenes/artistas/JoaquinRubioCamin.jpg',
      obraSeleccionada: 0,
    },
    {
      id: 7,
      nombre: 'Antonio',
      apellidos: 'Suárez',
      fechaNacimiento: '1923-01-26',
      fechaMuerte: '2013-10-21',
      lugarNacimiento: 'Gijón, Asturias',
      biografia: 'En 1957 fue uno de los miembros fundadores del Grupo El Paso, colectivo que introdujo el informalismo y la modernidad en la España de la posguerra. Evolucionó desde los tonos oscuros de su etapa en El Paso hacia una pintura de colores luminosos y marcada lírica visual.',
      estilo: 'Informalismo',
      imagen: '/assets/imagenes/artistas/AntonioSuarez.jpg',
      obraSeleccionada: 0,
    },
    {
      id: 8,
      nombre: 'José María',
      apellidos: 'Navascués',
      fechaNacimiento: '1934-09-20',
      fechaMuerte: '1979-11-11',
      lugarNacimiento: 'Madrid',
      biografia: 'Artista y escultor fundamental para la vanguardia asturiana y española de la segunda mitad del siglo XX. Su preocupación por las formas anatómicas y los procesos vitales le condujeron a la escultura, escogiendo la madera como materia primigenia.',
      estilo: 'Abstracción lírica',
      imagen: '/assets/imagenes/artistas/Navascues.jpg',
      obraSeleccionada: 0,
    },
    {
      id: 9,
      nombre: 'Mariano',
      apellidos: 'Moré Cors',
      fechaNacimiento: '1899-05-07',
      fechaMuerte: '1974-07-01',
      lugarNacimiento: 'Gijón, Asturias',
      biografia: 'Pintor e ilustrador asturiano, figura clave de la pintura regionalista asturiana del siglo XX. Su obra evolucionó desde un realismo de fuerte compromiso social hacia una visión más idealizada del paisaje y las costumbres asturianas.',
      estilo: 'Costumbrismo / Realismo',
      imagen: '/assets/imagenes/artistas/MarianoMore.jpg',
      obraSeleccionada: 0,
    },
    {
      id: 10,
      nombre: 'Pelayo',
      apellidos: 'Ortega',
      fechaNacimiento: '1956-05-01',
      fechaMuerte: null,
      lugarNacimiento: 'Mieres, Asturias',
      biografia: 'Destacado pintor y grabador asturiano, referente de la generación que revitalizó la pintura española en los años 80. Su obra se caracteriza por una síntesis entre la figuración y la abstracción, con un estilo poético y a menudo minimalista.',
      estilo: 'Arte contemporáneo / Figuración abstracta',
      imagen: '/assets/imagenes/artistas/PelayoOrtega.jpg',
      obraSeleccionada: 0,
    },
  ];

  // Índice del primer artista visible (avanza de 2 en 2)
  readonly indice = signal(0);

  readonly artistasVisibles = computed(() =>
    this.artistas.slice(this.indice(), this.indice() + 2)
  );

  readonly total = this.artistas.length;

  readonly puedeAnterior = computed(() => this.indice() > 0);
  readonly puedeSiguiente = computed(() => this.indice() + 2 < this.total);
  readonly indicePagina = computed(() => this.indice() / 2);
  readonly dotsArray = computed(() => Array(Math.ceil(this.total / 2)).fill(0));

  anterior(): void {
    if (this.puedeAnterior()) this.indice.update(i => i - 2);
  }

  siguiente(): void {
    if (this.puedeSiguiente()) this.indice.update(i => i + 2);
  }

  seleccionarObra(artista: Artista, index: number): void {
    artista.obraSeleccionada = index;
  }

  verMasInfo(artista: Artista): void {
    console.log('Ver más info de:', artista.nombre, artista.apellidos);
  }

  getNombreCompleto(artista: Artista): string {
    return `${artista.nombre} ${artista.apellidos}`;
  }

  getAnios(artista: Artista): string {
    const nac = artista.fechaNacimiento.substring(0, 4);
    const muerte = artista.fechaMuerte ? artista.fechaMuerte.substring(0, 4) : 'actualidad';
    return `${nac} – ${muerte}`;
  }
}

