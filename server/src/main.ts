import { NestFactory } from '@nestjs/core';
import { SwaggerModule, DocumentBuilder } from '@nestjs/swagger'
import { AppModule } from './app.module';
import { ValidationPipe } from './common/pipes/validate.pipe';
import { BusinessHttpExceptionFilter } from './common/filters/httpException.filter';
import { RequestTimeInterceptor } from './common/interceptors/requestTime.interceptor';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  // 请求时间
  app.useGlobalInterceptors(new RequestTimeInterceptor());

  // app.useGlobalInterceptors(new NotFoundInterceptor());
  // app.use(gateway);
  app.useGlobalPipes(new ValidationPipe());
   // 全局拦截业务错误，转化为 code
  app.useGlobalFilters(new BusinessHttpExceptionFilter());

  app.setGlobalPrefix('/api');

  // 设置swagger接口文档
  const options = new DocumentBuilder()
    .setTitle('zihenvip 接口文档')
    .setDescription('zihenvip 接口文档')
    .setVersion('1.0')
    .addBearerAuth()
    .build();
  const document = SwaggerModule.createDocument(app, options);
  // 设置 swagger 网址 http://localhost:3000/api-docs/
  SwaggerModule.setup('api-docs', app, document);

  await app.listen(3000);
}
bootstrap();
