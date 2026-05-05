export interface Obra {
  
  id: number;
  titulo: string;
  fechaCreacion?: number;
  tecnica?: string;
  descripcion: string;
  descripcionEn?: string;
  dimensiones?: string;
  artistaId: number;
  museoId?: number;
  epocaId?: number;
  artistaNombre?: string;
  museoNombre?: string;
  epocaNombre?: string;
}
