export interface Imagen {
  
  id: number;
  url: string;
  tipoEntidad: 'OBRA' | 'MONUMENTO';
  idEntidad: number;
  esPrincipal: boolean;
  orden: number;
}