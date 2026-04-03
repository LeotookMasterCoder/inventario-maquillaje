import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Producto } from '../models/producto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  private url = 'http://localhost:8081/productos';

  constructor(private http: HttpClient) {}

  getProductos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(this.url, {
      headers: new HttpHeaders({
        Authorization: 'Bearer TU_TOKEN_AQUI'
      })
    });
  }

  guardar(producto: Producto): Observable<any> {
    return this.http.post(this.url, producto, {
      headers: new HttpHeaders({
        Authorization: 'Bearer TU_TOKEN_AQUI'
      })
    });
  }
}
