import { PipeTransform, Injectable, ArgumentMetadata, BadRequestException, Type } from '@nestjs/common';
import { validate } from 'class-validator';
import { plainToClass } from 'class-transformer';

@Injectable()
export class ValidationPipe implements PipeTransform<any> {
  async transform<V = any>(value: V, { metatype }: ArgumentMetadata): Promise<V> {
    if (!metatype || !this.toValidate(metatype)) {
      return value;
    }
    const object = plainToClass(metatype, value);
    const errors = await validate(object);
    console.log('errors ==>', errors);
    if (errors.length > 0) {
      const firstConstraintsValues = Object.values(errors[0].constraints);
      throw new BadRequestException(firstConstraintsValues[0]);
    }
    return value;
  }

  private toValidate(metatype: Type<any>): boolean {
    const types: (Type<any>)[] = [String, Boolean, Number, Array, Object];
    return !types.includes(metatype);
  }
}