function TodoController($scope, $http) {
  
  $scope.todos = [];
  
  $scope.refreshTodos = function() {
    $http.get('api/todo').success(function(data) {
      $scope.todos = data;
    });
  };  
  
  $scope.refreshTodos();
  
  $scope.total = function() {
    return $scope.todos.length;
  };
  
  $scope.remaining = function() {
    return _.filter($scope.todos, function(todo) {
      return todo.done;
    }).length;
  };
  
  $scope.add = function() {
    if (this.text) {
      var text = this.text;
      this.text = "";
      $http({
        method: 'POST',
        url: 'api/todo',
        data: 'text=' + text,
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}        
      }).success(function(data) {
        $scope.refreshTodos();
      });      
    }
  };
  
  $scope.mark = function(todo) {
    $http({
      method: 'PUT',
      url: 'api/todo/' + todo.id,
      data: 'done=' + todo.done,
      headers: {'Content-Type': 'application/x-www-form-urlencoded'}        
    });
  };
  
  $scope.cleanup = function() {
    _.each($scope.todos, function(todo) {
      if (todo.done) {
        $http.delete('api/todo/' + todo.id);
      }
    });
    $scope.todos = _.reject($scope.todos, function(todo) {
      return todo.done;
    });
  };
}