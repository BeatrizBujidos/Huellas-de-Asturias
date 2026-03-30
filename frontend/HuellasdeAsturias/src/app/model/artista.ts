export interface Artista {
  id: number;
  nombre: string;
  apellidos: string;
  fechaNacimiento: Date;
  fechaMuerte?: Date;
  lugarNacimiento: string;
  biografia: string;
  estilo: string;
  imagen: string;
}
