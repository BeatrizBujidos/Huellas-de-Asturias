export interface Imagen {
  
  id: number;
  url: string;
  tipoEntidad: 'monumento' | 'obra';
  idEntidad: number;
  esPrincipal: boolean;
  orden: number;
}