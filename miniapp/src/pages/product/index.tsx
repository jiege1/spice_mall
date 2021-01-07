import React, { Component } from 'react'
import classNames from 'classnames'
import { View, ScrollView, Image, Text } from '@tarojs/components'
import { observer, inject } from 'mobx-react'
import { Store } from 'src/store'
import css from './index.module.less';


interface Props {
  store: Store
}

@inject('store')
@observer
export default class Product extends Component<Props> {
  componentWillMount () { }

  componentDidMount () { }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  render () {
    const { product } = this.props.store;
    return (
      <View className={css.container}>
        <ScrollView scrollY className={css.left}>
          <View className={css.box}>
            {
              product.categories.map(category => {
                return (
                  <View key={category.id} 
                    className={classNames(css.item, { [css.selected]: category.id === product.categoryId })}
                    onClick={() => product.update('categoryId', category.id)}
                  >
                    {category.title}
                  </View>
                );
              })
            }
          </View>
        </ScrollView>
        <ScrollView scrollY className={css.right}>
          <View>
            {
              product.selectProducts.map(item => {
                return (
                  <View key={item.id}>
                    <Image src={item.indexImage} />
                    <Text>{item.title}</Text>
                  </View>
                );
              })
            }
          </View>
        </ScrollView>
      </View>
    )
  }
}
