import { Module, NestModule, MiddlewareConsumer } from '@nestjs/common';
import { APP_GUARD } from '@nestjs/core';
import { TypeOrmModule } from '@nestjs/typeorm';
import {ConfigModule, ConfigService} from 'nestjs-config';
import * as path from 'path';
import { UsersModule } from './modules/users/users.module';
import { User } from './modules/users/entities/user.entity';
import { AuthModule } from './modules/auth/auth.module';
import { ProductModule } from './modules/product/product.module';
import { Product } from './modules/product/entities/product.entity';
import { CustomerModule } from './customer/customer.module';
import { OrderModule } from './order/order.module';

@Module({
  imports: [
    AuthModule,
    UsersModule,
    ConfigModule.load(path.resolve(__dirname, 'config', '**/!(*.d).{ts,js}'), {
      // 根据环境变量，注入不同的 .env 配置文件
      path: path.resolve(__dirname, '../env', `.${process.env.NODE_ENV}.env`)
    }),
    TypeOrmModule.forRootAsync({
      inject: [ConfigService],
      useFactory: (config: ConfigService) => ({
        ...config.get('database'),
        entities: [User, Product],
      }),
    }),
    ProductModule,
    CustomerModule,
    OrderModule,
    // AuthModule,
  ],
  controllers: [],
  providers: [
    // 全局注入 AuthGuard，作为 权限校验路由
    // {
    //   provide: APP_GUARD,
    //   useClass: AuthGuard,
    // },
  ],
})
export class AppModule implements NestModule {

  configure(consumer: MiddlewareConsumer): void {
    // setTimeout(() => {
    //   consumer
    //   .apply(AuthMiddleware)
    //   .forRoutes({ path: '*', method: RequestMethod.ALL });;
    // }, 1000);
  }

}
