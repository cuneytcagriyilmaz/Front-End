import React from 'react';
import Todo from '../Components/Todo'

function TodoList({todos,setTodos,filteredTodos}) {

	const completeAll = () => {
		setTodos(todos.map((todo) => {
				return {
					...todo, completed:true
				}
		}))
	}

	const handleEditTodos = (editValue, id) => {
		const newTodos = [...todos]
		newTodos.forEach((todo) => {
			if(todo.id === id){
				todo.text = editValue
			}
		})
		setTodos(newTodos)
	}

	return (
		<div>
			<section className="main">
				<input className="toggle-all" type="checkbox" />
				<label htmlFor="toggle-all" onClick={completeAll} >
					Mark all as complete
				</label>
				<ul className="todo-list">
					{filteredTodos.map(todo => {
						return(
							<Todo 
								todo={todo}
								todos={todos}
								setTodos={setTodos}
								key={todo.id}
								handleEditTodos={handleEditTodos}
							/>
						)
					})}
				</ul>
			</section>
		</div>
	);
}

export default TodoList;