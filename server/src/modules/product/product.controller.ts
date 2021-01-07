import { Controller, Get, UseGuards } from '@nestjs/common';
import { AuthGuard } from '@nestjs/passport';
import { Result, PagListResult, ListResult } from 'src/common/utils/result/result';
import { Product } from './entities/product.entity';
import { ProductService } from './product.service';
import { ApiTags } from '@nestjs/swagger';

@Controller('product')
@ApiTags('商品管理')
export class ProductController {

  constructor(private productService: ProductService) {}

  @Get()
  @UseGuards(AuthGuard('jwt'))
  async getProductList(): Promise<ListResult<Product>> {
    return Result.list<Product>(await this.productService.findList())
  }

}
