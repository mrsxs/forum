var app = new Vue({
        el: '#app',
        data: {
            userData: {}, // 用于存储用户数据
            post: [],
            comments: [],
            newPostData: {
                title: '',
                content: '',
            },

            showDialog: false,
            isExpanded: false
        },
        mounted: function () {
            axios.get('http://localhost:8080/users/isLogin').then((response) => {
                if (response.data.code === 20011) {
                } else {
                    //清空userData
                    this.userData = {};
                }
            }).catch((error) => {
                console.log(error);
            });

        var storedUserData = localStorage.getItem('userData');
        if(storedUserData) {
            const data = JSON.parse(storedUserData);
            const currentTime = new Date().getTime();

            if (currentTime < data.expirationTime) {
                // 数据未过期，将其设置给userData
                this.userData = data.userData;
            } else {
                // 数据已过期，删除它
                localStorage.removeItem('userData');
            }
        }

        // 获取文章列表
        axios.get('http://localhost:8080/posts').then((response) => {
            if (response.data.code === 20011) {
                this.post = response.data.data;
            } else {
                this.$message.error('获取文章失败');
            }
        }).catch((error) => {
            console.log(error);
        });
    },
    methods
:
{
    login: function () {
        // 跳转到登录页面
        window.location.href = "login.html";
    }
,
    out: function () {
        axios.get('http://localhost:8080/users/logout', {}).then((response) => {
            if (response.data.code === 20011) {
                this.$message.error('注销成功');
                //删除本地存储的用户数据
                localStorage.removeItem('userData');
                //刷新页面
                window.location.reload();
            } else {
                this.$message.error('注销失败');
            }
        }).catch((error) => {
            this.$message.error('请求失败，请稍后再试');
            console.log(error);
        });
    }
,
    newPost: function () {
        // 首先访问登录接口，如果返回的code是20011，说明用户已经登录，可以跳转到新建文章页面
        axios.get('http://localhost:8080/users/isLogin').then((response) => {
            if (response.data.code === 20011) {
                app.showDialog = true
            } else {
                this.$message.error('请先登录');
            }
        }).catch((error) => {
            console.log(error);
        });
    }
,
    showDia: function () {
        this.showDia = false;
        // 清空数据
        this.newPostData = {
            title: '',
            content: ''
        };
    }
,
    createPost: function () {
        //发送用户id 标题 内容
        axios.post('http://localhost:8080/posts', {
            user: {
                id: this.userData.id
            },
            title: this.newPostData.title,
            content: this.newPostData.content
        }).then((response) => {
            if (response.data.code === 20041) {
                this.$message({message: '创建成功', type: 'success'});
                //刷新页面
                window.location.reload();
            } else {
                this.$message.error('创建失败');
            }
        }).catch((error) => {
            this.$message.error('请求失败，请稍后再试');
            console.log(error);
        });
    }
,
    Details: function (postId) {
        // 这里的URL应该指向您的详情页URL
        // 假设详情页URL是 'details.html' 并且使用查询参数传递ID
        window.location.href = "details.html?id=" + postId;
    }
,
}
})
;