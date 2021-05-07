//表单验证
$('.ui.form')
    .form({
        on: 'blur',
        fields: {
            consignee: {
                identifier: 'consignee',
                rules: [
                    {
                        type: 'empty',
                        prompt: '收件人不能为空'
                    },
                    {
                        type: 'maxLength[10]',
                        prompt: '收件人姓名最长十位'
                    },
                ]
            },
            consigneePhone: {
                identifier: 'consigneePhone',
                rules: [
                    {
                        type: 'exactLength[11]',
                        prompt: '收件人手机号格式错误'
                    },
                ]
            },
            consigneeAddress: {
                identifier: 'consigneeAddress',
                rules: [
                    {
                        type: 'empty',
                        prompt: '收件人地址不能为空'
                    },
                    {
                        type: 'maxLength[30]',
                        prompt: '收件人地址最长三十位'
                    },
                ]
            },
            weight: {
                identifier: 'weight',
                rules: [
                    {
                        type: 'regExp[/^([0-9][0-9]*)+(.[0-9]{1,2})?$/]',
                        prompt: '快递重量必须为大于等于0的数字,最多有两位小数'
                    }
                ]
            },
        }
    });