export interface Artista {
  
  id: number;
  nombre: string;
  apellidos: string;
  fechaNacimiento: string;
  fechaMuerte?: string;
  lugarNacimiento: string;
  biografia: string;
  biografiaEn?: string;
  estilo: string;
  imagen: string;
}
