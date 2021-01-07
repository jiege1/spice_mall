import React, { Component } from 'react'
import { View, Button, Text } from '@tarojs/components'
import { observer, inject } from 'mobx-react'
import { Store } from 'src/store'
import css from './index.module.less';


interface Props {
  store?: Store
}

@inject('store')
@observer
export default class Index extends Component<Props> {
  componentWillMount () { }

  componentDidMount () { }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  render () {
    return (
      <View className={css.container}>
        <Text>我的</Text>
      </View>
    )
  }
}
