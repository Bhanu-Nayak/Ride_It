export function Input({ className, ...props }) {
  return (
    <input
      className={`w-full p-3 rounded-md border border-gray-700 bg-gray-800 focus:ring-2 focus:ring-yellow-500 ${className}`}
      {...props}
    />
  );
}
