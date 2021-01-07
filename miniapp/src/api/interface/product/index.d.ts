import { ProductStatusEnum } from './enums';

export interface ProductCategory {
  id: number;
  title: string;
  // url?: string;
  // parentId?: number;
}

export interface Product {
  id: number;
  title: string;
  price: number;
  status: ProductStatusEnum;
  images?: string;
  indexImage: string;
  video?: string;
  virtualSales?: number;
  sales?: number;
  categoryId?: number;
}
