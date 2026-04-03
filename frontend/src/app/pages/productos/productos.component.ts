import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductoService } from 'src/app/services/producto.service';
import { Producto } from 'src/app/models/producto';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css']
})
export class ProductosComponent implements OnInit {

  productos: Producto[] = [];
  form!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private service: ProductoService
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      nombre: ['', Validators.required],
      marca: [''],
      precio: [0, Validators.required],
      stock: [0, Validators.required]
    });

    this.listar();
  }

  listar(): void {
    this.service.getProductos().subscribe(data => {
      this.productos = data;
    });
  }

  guardar(): void {
    if (this.form.invalid) return;

    this.service.guardar(this.form.value).subscribe(() => {
      this.form.reset();
      this.listar();
    });
  }
}
