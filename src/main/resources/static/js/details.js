var app = new Vue({
    el: '#app',
    data: {
        userData: {},
        post: [],
        comments: [],
        comment: ""
    },
    mounted: function () {
        var storedUserData = localStorage.getItem('userData');
        if (storedUserData) {
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
        // 获取URL中的查询参数
        var queryParams = new URLSearchParams(window.location.search);
        var postId = queryParams.get('id'); // 获取id参数
        // 使用postId从后端获取详情数据...
        axios.get('http://localhost:8080/posts/' + postId).then((response) => {
            app.post = response.data.data;
        }).catch((error) => {
            console.log(error);
        });
        axios.get('http://localhost:8080/comments/' + postId).then((response) => {
            app.comments = response.data.data;
        }).catch((error) => {
            console.log(error);
        });
    },
    methods:{
        Reply: function () {
            if (app.comment === "") {
                this.$message.error('回复不能为空');
                return;
            }
            axios.get('http://localhost:8080/users/isLogin').then((response) => {
                if (response.data.code === 20011) {
                    // User is logged in, proceed with the comment submission
                    axios.post('http://localhost:8080/comments', {
                        content: app.comment,
                        pid: app.post.id,
                        user: {
                            id: app.userData.id
                        },
                    }).then((response) => {
                        if (response.data.code === 20041) {
                            // Comment submission successful
                            this.$message({message: '回复成功', type: 'success'});
                            window.location.reload();//刷新当前页面.
                        } else {
                            this.$message({message: response.data.msg, type: 'error'});
                        }
                    }).catch((error) => {
                        console.log(error);
                    });
                } else {
                    this.$message.error('请先登录');
                }
            }).catch((error) => {
                console.log(error);
            });
        }
    }
})