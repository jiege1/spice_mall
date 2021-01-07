import { Entity, Column, PrimaryGeneratedColumn } from 'typeorm';
import { IDBTableCommonKeys } from 'src/common/interface/index.interface';

export const enum ProductStatusEnum {
  UpShelf = 1,
  DownShelf = 2 ,
}

export interface IProduct extends IDBTableCommonKeys {
  title?: string;
  price?: number;
  status?: ProductStatusEnum;
  images?: string;
  indexImage?: string;
  video?: string;
  virtualSales?: number;
  sales?: number;
}

@Entity({ name: 'products' })
export class Product implements IProduct {

  @PrimaryGeneratedColumn()
  id: number;

  @Column({ name: 'create-time' })
  createTime?: Date;

  @Column({ name: 'update-time' })
  updateTime?: Date;

  @Column()
  title: string;

  @Column()
  price: number;

  @Column()
  status: ProductStatusEnum;

  @Column()
  images?: string;

  @Column({ name: 'index-image' })
  indexImage?: string;

  @Column()
  video?: string;

  @Column({ name: 'virtual-sales' })
  virtualSales?: number;

  @Column()
  sales?: number;
}