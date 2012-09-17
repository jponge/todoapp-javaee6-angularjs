function TodoController($scope) {
  
  $scope.idGen = 3;
  
  $scope.total = function() {
    return $scope.todos.length;
  };
  
  $scope.remaining = function() {
    return _.filter($scope.todos, function(todo) {
      return todo.done;
    }).length;
  };
  
  $scope.todos = [
    {id:1, text:"Wash the car", done:true},
    {id:2, text:"Shopping", done:false}
  ];
  
  $scope.add = function() {
    if (this.text) {
      $scope.todos.push({id:$scope.idGen, text:this.text, done:false});
      $scope.idGen = $scope.idGen + 1;
      this.text = "";
    }
  };
}